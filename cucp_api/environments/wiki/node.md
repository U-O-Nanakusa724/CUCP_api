# node.jsの導入

1. homebrew をインストール

```cmd
$ sudo yum groupinstall 'Development Tools' && sudo yum install curl file git ruby which
$ sh -c "$(curl -fsSL https://raw.githubusercontent.com/Linuxbrew/install/master/install.sh)"

インストール成功すると、下記のような文章が出てくる
==> Next steps:
- Add Homebrew to your PATH in /home/u_o_nanakusa724_engineer/.bash_profile:
echo 'eval $(/home/linuxbrew/.linuxbrew/bin/brew shellenv)' >> /home/u_o_nanakusa724_engineer/.bash_profile
eval $(/home/linuxbrew/.linuxbrew/bin/brew shellenv)

この２行をそのまま実行

$ brew -v 
バージョンが出ればOK
```

2. nodebrew をインストール

```cmd
$ brew install nodebrew
$ nodebrew -v
$ nodebrew setup

setupコマンドを実行すると、下記のような文章が出てくる
Fetching nodebrew...
Installed nodebrew in $HOME/.nodebrew
========================================
Export a path to nodebrew:
export PATH=$HOME/.nodebrew/current/bin:$PATH
========================================
```

3. パスを追加する

```cmd
$ vim ~/.bashrc
最終行に先ほどのパスを記入する
export PATH=$HOME/.nodebrew/current/bin:$PATH

$ source ~/.bashrc

※MacOSなら ~/.zshrc になる。OSに合わせること。
```

4. nodebrew を通じてnode をインストール

```cmd
$ nodebrew ls
下記が表示される
> not installed
> current: none

$ nodebrew ls-remote
インストールできるバージョン一覧が表示される

$ nodebrew install-binary v15.8.0
※利用したいバージョンを入力すること。

$ nodebrew ls   
下記のようにバージョンが表示されれば成功
> v15.8.0
> current: none

$ nodebrew use v15.8.0

$ nodebrew ls
下記のようにuseで指定したバージョンが表示されれば成功 
> v15.8.0
> current: v15.8.0

$ node -v
下記のように宣言したnodeが出ればOK
> v15.8.0
```

# Vue.jsのインストールとプロジェクト作成

1. vueのインストール

```cmd
$ npm install -g @vue/cli
```

2. vueプロジェクトを作成する

```cmd
$ cd CUCP_main
$ vue create cucp_front
※ **vueのプロジェクトは小文字しか使えないことに注意**

下記のように表示される
Vue CLI v4.5.11
? Please pick a preset: (Use arrow keys)
❯ Default ([Vue 2] babel, eslint) 
  Default (Vue 3 Preview) ([Vue 3] babel, eslint) 
  Manually select features 

方向キー上下で選択(今回はVue 3系を選択)

※ ディレクトリを削除したもののうまくcreateが通らない場合、下記コマンドで一度キャッシュをクリアする
$ npm cache clean --force

```

3. サーバを起動する