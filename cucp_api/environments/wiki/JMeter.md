# JMeterのダウンロード〜起動まで

A. サイトからzip(tar)をダウンロードする場合

1. https://jmeter.apache.org/download_jmeter.cgi からダウンロード
2. 任意のフォルダで展開
3. apache-jmeter-X.X.X/bin/ApacheJMeter.jarを起動

＊「開発者不明のため起動できません」と出た場合は、Ctrlを押しながら右クリック→開くで開けるようにすること。

B. homebrewを通してダウンロードする場合

1. brew install jmeter
2. インストールを待つ
3. jmeter
4. JMeterのGUIが出ればOK

＊brewを通した方法だと何故かJMeterのテストが保存できなかったので、以下Aの前提で進める。

## JMeter言語切り替え

Options > Choose Language > Jananese

＊以下、言語は英語のままであるとして進める。

# テスター作成

1. 左のシナリオ欄にあるTest Planを右クリック(Test Planの名前は変えてもOK)
2. Add > Threads(User) > Thread Group を押下
3. Thread GroupのNameやCommentsは任意に編集
4. Thread Propertiesを編集する

- Number of Threads(users): 立ち上げるスレッド数≒アクセス数、同時接続数
- Ramp-up period(seconds): どれくらいの時間をかけて全スレッドを終わらせるかの指定
- Loop Count: 上記セットを何回行うか

＊「Delay Thread creation until needed」にチェックを入れること。
でないと試験開始直後にスレッド数で指定した数だけスレッドが出来るため、負荷をかける側のメモリ圧迫などで試験が正常に動作しなくなる可能性がある。

5. 作成したThread Groupを右クリックし、addから以下を選択

- Sampler > HTTP Request: HTTPリクエストの設定を行う
- Config Element > HTTP Header Manager: Jsonリクエストを用いる場合に設定
- Listener > View Results Tree: テスト実行結果を表示。こちらは出したリクエストと受け取ったレスポンスの確認がしやすい。
- Listener > View Results in Table: テスト実行結果を表示。こちらは登録したテストたちの結果を一見しやすい。

6. HTTP Requestについて、以下の項目を適宜変更

- Protocol: httpかhttpsか、試験先に合わせて指定。
- Server Name or IP: 文字通り、試験先のIPアドレス。ローカルならlocalhostもOK。
- Port Number: こちらも試験先に合わせて指定。
- HTTP Request: 試験するメソッドとURLを記入する。
- Content encoding: 特に後述のBodyDataを利用する場合、ここにUTF8を指定すること。
- Parameters: リクエストパラムをここに設定する。
- Body Data: リクエストボディを利用する場合ここにJsonを記入。

7. HTTP Header Managerについて、以下を設定

- Name: Content-Type, Value: application/json
- Name: accept, Value: application/json

＊acceptいらないかも

8. Startを押下し、StatusがSuccessなら成功




