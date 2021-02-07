# データベースの準備

## 1.データベース、ユーザの初期セットアップ

1. アプリ用ユーザの作成

rootアカウントで実行。  
**-h, -P オプションをつけて、docker-compose内のデータベースに明示的に接続すると良い。**

```cmd

$ mysql -u root -h [GCEの外部IP] -P [docker-composeで指定したMySQLのポート] -p

mysql> SELECT user, host FROM mysql.user;
+---------------+-----------+
| user          | host      |
+---------------+-----------+
| mysql.session | localhost |
| mysql.sys     | localhost |
| root          | localhost |
+---------------+-----------+
3 rows in set (0.02 sec)

mysql> CREATE USER 'cucp' IDENTIFIED BY 'Cucp_2020';
Query OK, 0 rows affected (0.15 sec)

mysql> SELECT user, host FROM mysql.user;
+---------------+-----------+
| user          | host      |
+---------------+-----------+
| cucp          | %         |
| mysql.session | localhost |
| mysql.sys     | localhost |
| root          | localhost |
+---------------+-----------+
4 rows in set (0.00 sec)
```

2. ユーザに権限を与える

```cmd
mysql> GRANT ALL ON *.* TO cucp;
Query OK, 0 rows affected (0.02 sec)
```

3. MySQL Workbenchにて必要情報を入力し、新規追加したユーザでSelect文が実行できればＯＫ。
```sql
SELECT user, host FROM mysql.user
```

## 2.flywayを用いてマイグレーション実行

1. プロジェクトをCloneしておく。
2. ターミナルからプロジェクト直下に移動し、application-[dev/pro].ymlのflyway-settingsのurlのIPアドレスを該当サーバに変更する。  
   ※ローカルで行ってプッシュしてもOK。

3. 下記コマンドを実行し、StateがPendingであることを確認する。
```cmd
$ chmod 755 ./gradlew
$ ./gradlew flywayInfo -Penv=dev   

> Task :flywayInfo
Schema version: << Empty Schema >>
+-----------+---------+---------------+------+--------------+---------+
| Category  | Version | Description   | Type | Installed On | State   |
+-----------+---------+---------------+------+--------------+---------+
| Versioned | 0.0.0   | create tables | SQL  |              | Pending |
+-----------+---------+---------------+------+--------------+---------+

BUILD SUCCESSFUL in 3s
1 actionable task: 1 executed
```
> Pendingは「flywayとして認識しており、実行はしていない」というステータス。

4. 下記コマンドを実行する。
```cmd
$ chmod 755 ./gradlew
$ ./gradlew flywayMigrate -Penv=dev   

```

