# CUCP_api
CUCPのAPIプロジェクト。  

<details><summary>ディレクトリと役割</summary><div>

```dir
cucp_api
+ environments
|  + DB              :ER図
|  + system
|  + wiki 
+ 
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
