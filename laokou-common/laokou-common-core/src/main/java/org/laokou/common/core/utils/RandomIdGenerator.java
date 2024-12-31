package org.laokou.common.core.utils;

import java.security.SecureRandom;
import java.util.Base64;

/**
 * @author laokou
 */
public final class RandomIdGenerator {

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

	private static final Base64.Encoder ENCODER = Base64.getUrlEncoder();

	private static final SecureRandom RANDOM = new SecureRandom();

    // 默认的随机字符串长度
    private static final int DEFAULT_LENGTH = 16;

    /**
     * 生成一个随机的字符串 ID
     *
     * @return 随机生成的字符串 ID
     */
    public static String generateRandomId() {
        return generateRandomId(DEFAULT_LENGTH);
    }

    /**
     * 生成一个指定长度的随机字符串 ID
     *
     * @param length ID 的长度
     * @return 随机生成的字符串 ID
     */
    public static String generateRandomId(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = RANDOM.nextInt(ALPHABET.length());
            sb.append(ALPHABET.charAt(index));
        }
        return sb.toString();
    }

    /**
     * 生成一个随机的 Base64 编码的 ID（比普通的字符串 ID 更紧凑）
     *
     * @return Base64 编码的随机 ID
     */
    public static String generateDefaultBase64RandomId() {
		return generateBase64RandomId(DEFAULT_LENGTH);
    }

	public static String generateBase64RandomId(int length) {
		// 生成16字节的随机字节
		byte[] randomBytes = new byte[length];
		RANDOM.nextBytes(randomBytes);
		// 使用 URL-safe Base64 编码
		return ENCODER.encodeToString(randomBytes);
	}

}
