<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <!-- 文字コード -->
    <meta charset="UTF-8">

    <!-- Internet Explorer、エッジで使用できる互換モード機能 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

     <!-- ブラウザで表示されるタイトル -->
    <title>チーム開発</title>

    <!-- CSSファイル達の読み込み -->
    <link rel="stylesheet" href="../css/login.css">
    <link rel="stylesheet" href="../css/reset.css">
</head>
    <!-- body部分-->
    <body>
        <!-- #header-->
        <header id ="header">
            <div class="hd-wrapper">
                <div class="header-logo">
                    <img src="../images/logo01.png">
                </div>
                <div class="subtitle">
                    <p>Java Team Development</p>
                </div>
            </div>
        <!-- main部分-->
        <main>
            <!-- ログイン画面 -->
            <div class="login">
                <p class="large">ログイン</p>
            </div>
            <form action="LoginServlet" method="post" id="formAction">
                <div class="wrapper1">
                    <div class="lbl1"><p class ="sub">UserID</p></div>
                    <div class="lbl2"><input type="text" size="40" name="userId" id="userId"></div>
                </div>
                <div class="wrapper1">
                    <div class="lbl1"><p class ="sub">PassWord</p></div>
                    <div class="lbl2"><input type="password" size="40" name="pass" id="pass"></div>
                </div>
                <!-- 登録ボタン -->
                <div class="wrapper2">
                    <input type="button" size="20" name="register" id="register" value="登録">
                    <a class ="newRegister" href="#">新規会員登録はこちら</a>
                </div>
            </form>
        </main>
        </header>
    </body> 
</html>
