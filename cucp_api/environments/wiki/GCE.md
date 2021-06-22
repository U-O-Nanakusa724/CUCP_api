# 目的
GCEについて行なったことなどまとめる

## GCEインスタンス設定

無料枠に収まるサイズで作成する  
名前：cucp-dev-001  
リージョン(ゾーン)：us-west-b  
マシンの構成：「汎用」タブから下記を選択
- シリーズ：N1
- マシンタイプ：f1-micro

※実はマシンタイプのスペックは１つ上にしている(g1-なんとか)。無料の範疇っぽい？

ブートディスク：「公開イメージ」タブから下記を選択
- オペレーティングシステム：CentOS
- バージョン：CentOS 7
- ブートディスクの種類：標準の永続ディスク
- サイズ：20GB

ファイアウォール：HTTP,HTTPSどちらのチェックボックスにもチェック

## 起動確認
作成が成功したら、GCE一覧にある接続プルダウンから「ブラウザウィンドウで開く」を選択する。  
ブラウザ上のターミナルが出れば成功。

## 外部からのアクセス許可
VPCネットワーク > ファイアウォール と進み、「ファイアウォールルールを作成」を選択。  
下記のように設定する。

- 方向：上り
- ターゲット：指定されたターゲット
- ターゲットタグ：GCEのコンテナ名
- ソースフィルタ：IP範囲
- ソースIPの範囲：0.0.0.0/0
- プロトコルとポート：指定したプロトコルとポートを選び、tcpにポートフォワーディングしたいものを選ぶ

## 諸々のインストール

以下、インストールしたものとコマンド

- MySQL

1. 下記コマンドでインストール

```cmd
$ sudo yum localinstall http://dev.mysql.com/get/mysql57-community-release-el7-7.noarch.rpm 
$ sudo yum install -y mysql-community-server
$ mysqld --version
$ sudo systemctl start mysqld
$ sudo systemctl status mysqld
activeになっていればOK
$ sudo systemctl enable mysqld
```

2. 下記コマンドでroot初期パスワードを取得し、mysqlに入る

```cmd
$ sudo cat /var/log/mysqld.log | grep root
2021-01-09T04:42:39.886115Z 1 [Note] A temporary password is generated for root@localhost: H3p)6fwA1oki
$ mysql -u root -p
```

3. 下記コマンドでMySQLのルートパスワード変更と文字コードを確認する

```cmd
mysql> SET PASSWORD = PASSWORD('任意のパスワード');
mysql> show variables like "chara%";
+--------------------------+----------------------------+
| Variable_name            | Value                      |
+--------------------------+----------------------------+
| character_set_client     | utf8                       |
| character_set_connection | utf8                       |
| character_set_database   | latin1                     |
| character_set_filesystem | binary                     |
| character_set_results    | utf8                       |
| character_set_server     | latin1                     |
| character_set_system     | utf8                       |
| character_sets_dir       | /usr/share/mysql/charsets/ |
+--------------------------+----------------------------+
mysql> exit
```

4. MySQLの設定を編集し、文字コードを変更する

```cmd
$ sudo vi /etc/my.cnf
$ sudo systemctl restart mysqld
$ mysql -u root -p

mysql> show variables like "chara%";
+--------------------------+----------------------------+
| Variable_name            | Value                      |
+--------------------------+----------------------------+
| character_set_client     | utf8                       |
| character_set_connection | utf8                       |
| character_set_database   | utf8                       |
| character_set_filesystem | binary                     |
| character_set_results    | utf8                       |
| character_set_server     | utf8                       |
| character_set_system     | utf8                       |
| character_sets_dir       | /usr/share/mysql/charsets/ |
+--------------------------+----------------------------+
```

***

- docker及びdocker-compose

1. SeLinux無効化

```cmd
$ getenforce
Enforcing
$ sudo setenforce 0
$ getenforce
Permissive
$ sudo vi /etc/sysconfig/selinux

# This file controls the state of SELinux on the system.
# SELINUX= can take one of these three values:
#     enforcing - SELinux security policy is enforced.
#     permissive - SELinux prints warnings instead of enforcing.
#     disabled - No SELinux policy is loaded.
#SELINUX=enforcing　　　＜ーーここをdisableに変更する。既存のものをコメントアウトするのが無難。
SELINUX=disabled
# SELINUXTYPE= can take one of three two values:
#     targeted - Targeted processes are protected,
#     minimum - Modification of targeted policy. Only selected processes are protected.
#     mls - Multi Level Security protection.
SELINUXTYPE=targeted
```

2. 既存のDockerがあれば削除する(今回はないので割愛)
3. 下記コマンドでDockerをインストールしDockerサービスを起動できるか確認

```cmd
$ sudo yum install -y yum-utils device-mapper-persistent-data lvm2
$ sudo yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo
$ sudo yum install -y docker-ce docker-ce-cli containerd.io
$ sudo service docker start
$ sudo service docker status
Docekerのサービスが稼働していればOK
```

4. 下記コマンドでDocker-compose インストール

```cmd
$ sudo curl -L "https://github.com/docker/compose/releases/download/1.27.4/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
$ sudo chmod +x /usr/local/bin/docker-compose
$ sudo ln -s /usr/local/bin/docker-compose /usr/bin/docker-compose
$ sudo docker-compose
docker-composeのコマンド一覧が出ればOK
```

***

- httpサーバ

1. 下記コマンドでインストール

```cmd
$ sudo yum install httpd -y
$ sudo systemctl start httpd.service
$ sudo systemctl status httpd.service
activeになっていればOK
```

2. http://[GCEのVMインスタンスの外部IPアドレス]にアクセスし、ApacheのTop画面が出ればサーバ立ち上げ及びファイアウォール設定が正しいことが確認できる。

***

- Java

```cmd
$ java -version
$ sudo yum install -y java-11-openjdk which
$ java -version
```

***

- git

```cmd
$ sudo yum install git
```

***

- pip

dockerコマンドが通らなかった際に「pipを通してインストールするライブラリが足りない」と  
表示されたためにdev環境では入れたが、  
結果dockerコマンドにsudoをつけなかったことが原因で必要ライブラリにアクセスできないだけだった。  
proには入れてないため割愛する。


Curl更新
$ sudo yum install epel-release
$ sudo yum install libnghttp2

$ sudo nano /etc/yum.repos.d/city-fan.repo

下記を記載
```
[CityFan]
name=City Fan Repo
baseurl=http://www.city-fan.org/ftp/contrib/yum-repo/rhel$releasever/$basearch/
enabled=1
gpgcheck=0
```

$ sudo yum clean all
$ sudo yum install curl 



Git
# インストールに必要なものを用意
$ sudo yum install wget
$ sudo yum -y install make
$ sudo yum install gcc
$ sudo yum install perl-ExtUtils-MakeMaker

# Gitが依存するライブラリの準備
$ sudo yum -y install curl-devel expat-devel gettext-devel openssl-devel zlib-devel

# 既存のGit削除
sudo -E yum -y remove git
git --version

# wgetコマンドでtarファイル取得し解凍
sudo wget https://mirrors.edge.kernel.org/pub/software/scm/git/git-2.9.5.tar.gz
sudo tar xzvf git-2.9.5.tar.gz

# makeコマンドでインストール
$ cd git-2.9.5
$ sudo make prefix=/usr/local all
$ sudo make prefix=/usr/local install


## 遭遇したトラブルと対処方など

- メモリ容量が足りずTomcatやdockerなどの主要コンテンツのインストールができない

かなり小さなGCEを建てているのでシンプルにスペック不足かもしれないと思ったが、  
下記サイトの手順を踏んでswap領域を作成すると解決できる。
https://qiita.com/madaran0805/items/ae0532a7436e1c684e72

※pro環境構築時は聞かれなかったのでやっていない。必須ではない模様。
