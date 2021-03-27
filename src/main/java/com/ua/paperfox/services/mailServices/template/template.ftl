<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${title}</title>
    <link rel="stylesheet" href="style.css">
</head>

<body>
<div class="container">
    <table>

        <table class="header">
            <tr>
                <td>
                    <a href="paperfox.com.ua">
                        <div class="logo"></div>
                    </a>
                </td>
            </tr>
        </table>

        <table class="section row">
            <tr>
                <td class="half">
                    <p class="text_align_left label">Замовлення</p>
                    <p class="order_id">${orderId}</p>
                </td>
                <td class="half">
                    <p class="text_align_right label">Сума замовлення</p>
                    <p class="order_id text_align_right">0 грн.</p>
                </td>
            </tr>
        </table>

        <table class="section row">
            <tr>
                <td class="label text_align_left" width="110px">
                    <p>Замовник:</p>
                    <p>Телефон:</p>
                    <p>e-mail:</p>
                    <p>Дата:</p>
                </td>
                <td class="info text_align_left info">
                    <p>Volodymyr Vlasov</p>
                    <p><a class="info" href="tel:+380635825280">+38 063 582 52 80</a></p>
                    <p><a class="info" href="mailto:volodymyr.vlasov@gmail.com">volodymyr.vlasov@gmail.com</a></p>
                    <p>23.02.2021 22:55</p>
                </td>
                <td>
                    <img class="delivery_method_logo" src="https://paperfox.com.ua/wp-content/uploads/2021/02/np_logo.png" alt="delivery_method_logo">
                </td>
            </tr>
        </table>


        <table class="row section  table_title">
            <tr>
                <td class="product_name_title label text_align_left">
                    <p>Товар</p>
                </td>
                <td class="product_quantity_title label text_align_center">
                    <p>Кількість</p>
                </td>
                <td class="product_price_title label text_align_center">
                    <p>Вартість</p>
                </td>
            </tr>
        </table>

        <table class="row item_container">
            <tr>
                <td class="product_description text_align_left label">
                    <!-- product description -->
                    <p class="label_small">Звичайний друк</p>
                    <p class="info">А3 - чорно-білий</p>
                    <p class="label_small">Файли для друку:</p>
                    <p class="label_file_list">file 1: Файл для печати.txt</p>
                    <p class="label_file_list">file 2: 1151690054_202102091527_info_inc.pdf</p>
                </td>
                <td class="product_quantity_value label">
                    <!-- quantity -->
                    <p>null</p>
                </td>
                <td class="product_price_value label">
                    <!-- price -->
                    <p>null</p>
                </td>
            </tr>

        </table>
        <table class="row item_container">
            <tr>
                <td class="product_description text_align_left label">
                    <!-- product description -->
                    <p class="label_small">Звичайний друк</p>
                    <p class="info">А3 - чорно-білий</p>
                    <p class="label_small">Файли для друку:</p>
                    <p class="label_file_list">file 1: Файл для печати.txt</p>
                    <p class="label_file_list">file 2: 1151690054_202102091527_info_inc.pdf</p>
                </td>
                <td class="product_quantity_value label">
                    <!-- quantity -->
                    <p>null</p>
                </td>
                <td class="product_price_value label">
                    <!-- price -->
                    <p>null</p>
                </td>
            </tr>

        </table>


        <#list orders as order>
            <div>${order}</div>
        </#list>

        <table class="section row">
            <tr>
                <td style="width: 330px;">
                    <table>
                        <tr>
                            <td style="width: 32px !important; ">
                                <img class="ico32" src="https://paperfox.com.ua/wp-content/uploads/2021/02/np_logo.png" alt="logo">
                            </td>
                            <td class="text_align_left">

                                <p class="info" style="line-height: 1em; padding-left: 20px">Нова Пошта</p>
                            </td>
                            <td style="padding-left: 50px; ">
                                <a class="ico32" href=""><img src="https://paperfox.com.ua/wp-content/uploads/2021/03/pdf.png" alt=""></a>
                            </td>
                        </tr>
                    </table>
                </td>
                <td>
                    <a href="/">
                        <table>
                            <tr>
                                <td><img src="https://paperfox.com.ua/wp-content/uploads/2021/03/open-folder.png " alt="folder ">
                                <td>
                                    <p class="info text_align_left" style="line-height: 1em; padding-left: 20px">Open folder</p>
                                </td>
                            </tr>
                        </table>
                    </a>
                </td>

            </tr>

        </table>
        <table style="padding: 10px 0 20px 20px;">
            <tr class="label text_align_left">
                <td style="padding-right: 20px;">Отримувач:</td>
                <td>Володимир Власов</td>
            </tr>
            <tr class="label text_align_left ">
                <td style="padding-right: 20px;">Телефон:</td>
                <td>+38 063 582 52 80</td>
            </tr>
            <tr class="label text_align_left ">
                <td style="padding-right: 20px;">Місто:</td>
                <td>м. Київ</td>
            </tr>
            <tr class="label text_align_left ">
                <td style="padding-right: 20px;">Відділення:</td>
                <td>57, Костянтинівська 8</td>
            </tr>
        </table>

    </table>
</div>
<style>

    * {
        font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
    }

    body {
        background-color: rgb(235, 235, 235);
        margin-top: 25px;
    }

    a {
        text-decoration: none;
    }

    td {
        border-collapse: collapse;
        /* border: 1px solid black; */
    }

    p {
        margin: 0;
        padding: 0;
        line-height: 2em;
        /* border: 1px solid black; */
    }

    .info {
        color: #656565 !important;
        font-size: 16px !important;
        font-weight: 700;
        line-height: 1.8em;
    }

    .label {
        color: #767676 !important;
        font-weight: 300;
    }

    .label_small {
        color: #767676 !important;
        font-size: 12px;
        font-weight: 300;
    }

    .label_file_list {
        color: #767676 !important;
        font-size: 12px;
        line-height: 1.4em;
        font-weight: 300;
    }

    .header {
        background-color: gray !important;
        justify-content: space-between;
        align-items: center;
        width: 100%;
        height: 60px;
    }

    .section {
        margin-top: 10px !important;
        width: 100%;
    }

    .row {
        padding-left: 20px !important;
        padding-right: 20px !important;
    }

    .logo {
        width: 180px;
        height: 100%;
        margin: 0;
        background-image: url("https://paperfox.com.ua/wp-content/uploads/2016/10/paperfox-logo-header-500x110-1.png");
        background-repeat: no-repeat;
        background-position: center;
        background-size: contain;
    }

    .product_link_page {
        color: #b7b7b7;
        line-height: 100%;
        text-decoration: none;
        text-align: right;
    }

    .half {
        width: 300px !important;
    }

    .text_align_left {
        text-align: left !important;
    }

    .text_align_right {
        text-align: right !important;
    }

    .text_align_center {
        text-align: center !important;
    }

    .order_id {
        color: black;
        text-align: left;
        font-size: 24px;
        font-weight: 700;
        line-height: 1em;
    }

    .delivery_method_logo {
        width: 50px;
        height: 50px;
        margin: 0;
    }

    .ico32 {
        width: 32px;
        height: 32px;
        margin: 0;
    }

    .container {
        margin-top: 20px;
        width: 100% !important;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.2) !important;
        max-width: 600px !important;
        margin: 0 auto !important;
        text-align: center;
        background-color: white;
    }

    .table_title {
        display: flex;
        align-items: center;
        background-color: #eeeeee;
        height: 25px;
    }

    .product_name_title {
        border-right: 1px solid white;
        width: 330px;
    }

    .product_quantity_title {
        border-right: 1px solid white;
        width: 107px;
    }

    .product_price_title {
        width: 107px;
    }

    .item_container {
        padding-bottom: 5px !important;
        border-bottom: 2px solid #eeeeee !important;
    }

    .product_description {
        width: 330px !important;
        padding: 0px 5px 5px 0px;
    }

    .product_quantity_value {
        width: 107px !important;
    }

    .product_price_value {
        width: 107px !important;
    }
</style>
</body>

</html>