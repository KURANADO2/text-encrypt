package com.kuranado.encrypt;

import lombok.extern.slf4j.Slf4j;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 加密文本入口
 *
 * @author Xinling Jing
 * @date 2022-04-21 09:52
 */
@SpringBootApplication(scanBasePackages = {"com.kuranado.encrypt"})
@Slf4j
public class TextEncryptApplication implements CommandLineRunner {

    @Autowired
    private StringEncryptor encryptor;

    private static final String HELP_COMMAND = "--help";

    private static final String ENCRYPT_COMMAND = "--encrypt";

    private static final String DECRYPT_COMMAND = "--decrypt";

    private static final String USAGE = "用法：\n" +
        "--help                   显示帮助\n" +
        "--encrypt 待加密字符串     加密信息\n" +
        "--decrypt 待解密字符串     解密信息";

    public static void main(String[] args) {
        SpringApplication.run(TextEncryptApplication.class, args);
    }

    @Override
    public void run(String... args) {

        if (args.length == 0) {
            log.info("未传入任何参数");
            log.info(USAGE);
            return;
        }

        // 查看帮助
        if (HELP_COMMAND.equals(args[0])) {
            log.info(USAGE);
            return;
        }

        // 判断第一个参数是否正确
        if (!ENCRYPT_COMMAND.equals(args[0]) && !DECRYPT_COMMAND.equals(args[0])) {
            log.info(USAGE);
            return;
        }

        // 参数长度不够
        if (args.length <= 1) {
            log.info(USAGE);
            return;
        }

        // 加密
        if (ENCRYPT_COMMAND.equals(args[0])) {
            encrypt(args);
        }

        // 解密
        if (DECRYPT_COMMAND.equals(args[0])) {
            decrypt(args);
        }
    }

    private void encrypt(String... args) {
        for (int i = 1; i < args.length; i++) {
            log.info("明文：{}，密文：{}", args[i], encryptor.encrypt(args[i]));
        }
    }

    private void decrypt(String... args) {
        for (int i = 1; i < args.length; i++) {
            try {
                String plain = encryptor.decrypt(args[i]);
                log.info("密文：{}，明文：{}", args[i], plain);
            } catch (Exception e) {
                log.error("密文：{} 解密失败", args[i]);
            }
        }
    }
}
