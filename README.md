## 说明

此工程用于基于 jasypt 加密敏感信息

## 打包

```shell
cd text-encrypt
mvn clean package
cd target
```

## 使用方法
### 查看帮助

```shell
$ java -Djasypt.encryptor.password=密钥 -jar text-encrypt.jar --help
```

### 加密

```shell
$ java -Djasypt.encryptor.password=密钥 -jar text-encrypt.jar --encrypt 待加密字符串1 待加密字符串2 待加密字符串3 ... 待加密字符串n
```

### 解密

```shell
$ java -Djasypt.encryptor.password=密钥 -jar text-encrypt.jar --decrypt 待解密字符串1 待解密字符串2 待解密字符串3 ... 待解密字符串n
```

## 示例

使用密钥 `Om,@#YivWa*Rez&~8^%BcG` 对敏感信息加密。若传入多个字符串，请使用空格隔开；若字符串内部存在空格，则请加上双引号：

```shell
$ java -Djasypt.encryptor.password='Om,@#YivWa*Rez&~8^%BcG' -jar text-encrypt.jar --encrypt 123456 789 "Hello World"
明文：123456，密文：gieIzx7GVZ2TyGpFMvYe103R0I6SneaJJboxOxYiPZ2TffoNOxqzdUMYdb7sVKS3
明文：789，密文：JemMkiJsnpjKxOh4ygO+WANtwDU43L4m8gL3v4wm0/zMOQdSiODyS/uz4j6On0nF
明文：Hello World，密文：TXqB2l3tdqlzWupMfn/ro4IbMLka4GLDujC4ZvxxRW8LesxUsf9H5uOs5BFqF0H2
```

使用密钥 `Om,@#YivWa*Rez&~8^%BcG` 对加密字符串解密。若传入多个加密字符串，请使用空格隔开：

```shell
$ java -Djasypt.encryptor.password='Om,@#YivWa*Rez&~8^%BcG' -jar text-encrypt.jar --decrypt "gieIzx7GVZ2TyGpFMvYe103R0I6SneaJJboxOxYiPZ2TffoNOxqzdUMYdb7sVKS3" "JemMkiJsnpjKxOh4ygO+WANtwDU43L4m8gL3v4wm0/zMOQdSiODyS/uz4j6On0nF" "TXqB2l3tdqlzWupMfn/ro4IbMLka4GLDujC4ZvxxRW8LesxUsf9H5uOs5BFqF0H2"
密文：gieIzx7GVZ2TyGpFMvYe103R0I6SneaJJboxOxYiPZ2TffoNOxqzdUMYdb7sVKS3，明文：123456
密文：JemMkiJsnpjKxOh4ygO+WANtwDU43L4m8gL3v4wm0/zMOQdSiODyS/uz4j6On0nF，明文：789
密文：TXqB2l3tdqlzWupMfn/ro4IbMLka4GLDujC4ZvxxRW8LesxUsf9H5uOs5BFqF0H2，明文：Hello World
```