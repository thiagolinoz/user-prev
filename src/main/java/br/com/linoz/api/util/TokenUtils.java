package br.com.linoz.api.util;

import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;

import br.com.linoz.api.entity.Role;
import io.smallrye.jwt.build.Jwt;
import io.smallrye.jwt.build.JwtClaimsBuilder;

@ApplicationScoped
public class TokenUtils {
	
	public String generateToken(String username, Set<Role> roles, Long duration, String issuer) 
			throws Exception {
				String privateKeyLocation = "/privatekey.pem";
				PrivateKey privateKey = readPrivateKey(privateKeyLocation);
				
				JwtClaimsBuilder claimsBuilder = Jwt.claims();
				long currentTimeInSecs = currentTimeInsSecs();
				
				Set<String> groups = new HashSet<>();
				for (Role role : roles) groups.add(role.toString());
				
				claimsBuilder.issuer(issuer);
				claimsBuilder.subject(username);
				claimsBuilder.issuedAt(currentTimeInSecs);
				claimsBuilder.expiresAt(currentTimeInSecs + duration);
				claimsBuilder.groups(groups);
				
				return claimsBuilder.jws().signatureKeyId(privateKeyLocation).sign(privateKey);
	}

	private PrivateKey readPrivateKey(final String pemResName) throws Exception {
		try(InputStream contentIS = TokenUtils.class.getResourceAsStream(pemResName)) {
			byte[] tmp = new byte[4096];
			int length = contentIS.read(tmp);
			return decodePrivateKey(new String(tmp, 0, length, "UTF-8"));
		}
	}

	private PrivateKey decodePrivateKey(final String pemEncoded) throws Exception {
		byte[] encodedBytes = toEncodedBytes(pemEncoded);
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encodedBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		
		return kf.generatePrivate(keySpec);
	}

	private byte[] toEncodedBytes(String pemEncoded) {
		final String normalizePem = removeBeginEnd(pemEncoded);
		return Base64.getDecoder().decode(normalizePem);
	}

	private String removeBeginEnd(String pem) {
		pem = pem.replaceAll("-----BEGIN (.*)-----", "");
		pem = pem.replaceAll("-----END (.*)----", "");
		pem = pem.replaceAll("\r\n", "");
		pem = pem.replaceAll("\n", "");
		return pem.trim();
	}

	private int currentTimeInsSecs() {
		long currentTimeMS = System.currentTimeMillis();
		return (int) (currentTimeMS / 1000);
	}

}
