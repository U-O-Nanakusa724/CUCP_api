## 手順
1. GCE上でプロジェクト直下に移動し、 git pull を行う

```git
$ cd /home/cucp/git/CUCP
$ git pull origin main
```

2. application-pro.ymlのIPアドレスおよびパスワードを正しいものに編集する

3. 下記コマンドを実行する

```cmd
$ chmod 755 ./gradlew
$ ./gradlew bootJar
```

3. 下記ディレクトリにて、シェルを起動する
```cmd
$ cd environments/system

起動するとき
$ sh upJar.sh

停止するとき
$ sh downJar.sh

```

## 手動の手順(2021/1/14より、シェル起動形式に変更した。行っていることは概ね同じ。)
1. GCE上でプロジェクト直下に移動し、 git pull を行う

```git
$ cd /home/cucp/git/CUCP
$ git pull origin main
```

2. 下記コマンドを実行する

```cmd
$ chmod 755 ./gradlew
$ ./gradlew bootJar
```

3. 同じディレクトリに build/libs/[アプリ名].jarができるので、それを実行する

```cmd
検証環境のコマンド
$ java -jar build/libs/cucp-0.0.1-SNAPSHOT.jar -Dspring.profiles.active=dev
こちらは止めたくなったら ctrl + C で停止可能。

本番環境のコマンド
$ nohup java -jar build/libs/cucp-0.0.1-SNAPSHOT.jar -Dspring.profiles.active=pro
こちらもSSHのターミナルが開いている限りは ctrl + C で停止させられるが、閉じた場合はタスクキルしにいく必要あり。  
単なる動作確認として起動する際に利用するのはおすすめしない。
```

注意点など
- -Dspring.profiles.activeオプションで実行するymlファイルを選択する。
- nohupを先頭に付けることでSSH通信を終えてもアクセス可能(バックグラウンド実行)にできる。
