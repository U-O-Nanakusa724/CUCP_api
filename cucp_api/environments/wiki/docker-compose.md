## 忘れがちな準備

GCPのコンテナ起動時、必ず下記コマンドを実行すること。

```cmd
sudo service docker start
```
> dockerサービスそのものを起動しないとdockerコマンド自体通らない。

## docker-composeコマンド

いずれも、docker-compose.ymlがあるディレクトリで行うこと。

- 現在の状態確認

```cmd
sudo docker-compose ps
```

- 起動

```cmd
sudo docker-compose up -d
```

- 停止

```cmd
sudo docker-compose down
```

## docker-compose.ymlの雑な解説

随時追記。