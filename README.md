# CUCP_api
CUCPのAPIプロジェクト。  

<details><summary>ディレクトリと役割</summary><div>

```dir
cucp_api
+ environments       :docker-composeなど環境に関するファイルを格納
|  + DB              :ER図を格納
|  + system          :サービスの起動・停止シェルを格納
|  + wiki            :解説マークダウンを格納(GithubのWikiが復帰したら削除するかも)
+ flyway
|  + seed            :サンプルデータをInsertするクエリファイルを格納する
|  + sql             :flywayマイグレーション実行ファイルを格納する
+ src/main
|  + java/biz/uoray/cucp
|  |   + config      :SpringBootやSwaggerなどのコンフィグクラスを格納する
|  |   + controller  :コントローラ層を格納する
|  |   + dto         :EntityをそのままJsonにできないときなどに中継させるクラス
|  |   + request     :フロントから入力されたデータ(Json)をJavaオブジェクトに変換するクラス
|  |   + response    :JavaオブジェクトをJsonにしてフロントに渡す
|  |   + service     :サービス層を格納する
|  + resource        :様々なリソースを配置、詳細は割愛
+ build.gradle       :apiが依存するライブラリやbootタスクなどの定義を書く
+ setting.gradle     :commonへの依存を定義している

```

</div></details>

## Swagger
http://localhost:8080/swagger-ui.html
