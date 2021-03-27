package com.ua.paperfox.services.mailServices;

public class LetterTemplates {
    public static final String MAIL_NEW_ODRER = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "    <title>Document</title>\n" +
            "    <!-- <link rel=\"stylesheet\" href=\"style.css\"> -->\n" +
            "\n" +
            "    <style>\n" +
            "        /*  \n" +
            "*   https://paperfox.com.ua/wp-content/uploads/2021/02/np_logo.png\n" +
            "*   https://paperfox.com.ua/wp-content/uploads/2021/02/paperfox_logo.png\n" +
            "*   https://paperfox.com.ua/wp-content/uploads/2021/02/uklon_logo.png\n" +
            "*/\n" +
            "        \n" +
            "        * {\n" +
            "            font-family: \"Helvetica Neue\", Helvetica, Arial, sans-serif;\n" +
            "        }\n" +
            "        \n" +
            "        body {\n" +
            "            background-color: rgb(235, 235, 235);\n" +
            "            margin-top: 25px;\n" +
            "        }\n" +
            "        \n" +
            "        td {\n" +
            "            border-collapse: collapse;\n" +
            "            /* border: 1px solid black; */\n" +
            "        }\n" +
            "        \n" +
            "        p {\n" +
            "            margin: 0;\n" +
            "            padding: 0;\n" +
            "            line-height: 2em;\n" +
            "            /* border: 1px solid black; */\n" +
            "        }\n" +
            "        \n" +
            "        .info {\n" +
            "            color: #656565 !important;\n" +
            "            font-size: 16px !important;\n" +
            "            font-weight: 700;\n" +
            "            line-height: 1.8em;\n" +
            "        }\n" +
            "        \n" +
            "        .label {\n" +
            "            color: #767676 !important;\n" +
            "            font-weight: 300;\n" +
            "        }\n" +
            "        \n" +
            "        .label_small {\n" +
            "            color: #767676 !important;\n" +
            "            font-size: 12px;\n" +
            "            font-weight: 300;\n" +
            "        }\n" +
            "        \n" +
            "        .label_file_list {\n" +
            "            color: #767676 !important;\n" +
            "            font-size: 12px;\n" +
            "            line-height: 1.4em;\n" +
            "            font-weight: 300;\n" +
            "        }\n" +
            "        \n" +
            "        .header {\n" +
            "            background-color: gray !important;\n" +
            "            justify-content: space-between;\n" +
            "            align-items: center;\n" +
            "            width: 100%;\n" +
            "            height: 60px;\n" +
            "        }\n" +
            "        \n" +
            "        .section {\n" +
            "            margin-top: 20px !important;\n" +
            "            width: 100%;\n" +
            "        }\n" +
            "        \n" +
            "        .row {\n" +
            "            padding-left: 20px !important;\n" +
            "            padding-right: 20px !important;\n" +
            "        }\n" +
            "        \n" +
            "        .logo {\n" +
            "            width: 180px;\n" +
            "            height: 100%;\n" +
            "            margin: 0;\n" +
            "            background-image: url(\"https://paperfox.com.ua/wp-content/uploads/2016/10/paperfox-logo-header-500x110-1.png\");\n" +
            "            background-repeat: no-repeat;\n" +
            "            background-position: center;\n" +
            "            background-size: contain;\n" +
            "        }\n" +
            "        \n" +
            "        .product_link_page {\n" +
            "            color: #b7b7b7;\n" +
            "            line-height: 100%;\n" +
            "            text-decoration: none;\n" +
            "            text-align: right;\n" +
            "        }\n" +
            "        \n" +
            "        .half {\n" +
            "            width: 300px !important;\n" +
            "        }\n" +
            "        \n" +
            "        .text_align_left {\n" +
            "            text-align: left !important;\n" +
            "        }\n" +
            "        \n" +
            "        .text_align_right {\n" +
            "            text-align: right !important;\n" +
            "        }\n" +
            "        \n" +
            "        .text_align_center {\n" +
            "            text-align: center !important;\n" +
            "        }\n" +
            "        \n" +
            "        .order_id {\n" +
            "            color: black;\n" +
            "            text-align: left;\n" +
            "            font-size: 24px;\n" +
            "            font-weight: 700;\n" +
            "            line-height: 1em;\n" +
            "        }\n" +
            "        \n" +
            "        .delivery_method_logo {\n" +
            "            width: 50px;\n" +
            "            height: 50px;\n" +
            "        }\n" +
            "        \n" +
            "        .container {\n" +
            "            margin-top: 20px;\n" +
            "            width: 100% !important;\n" +
            "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2) !important;\n" +
            "            max-width: 600px !important;\n" +
            "            margin: 0 auto !important;\n" +
            "            text-align: center;\n" +
            "            background-color: white;\n" +
            "        }\n" +
            "        \n" +
            "        .table_title {\n" +
            "            display: flex;\n" +
            "            align-items: center;\n" +
            "            background-color: #eeeeee;\n" +
            "            height: 25px;\n" +
            "        }\n" +
            "        \n" +
            "        .product_name_title {\n" +
            "            border-right: 1px solid white;\n" +
            "            width: 330px;\n" +
            "        }\n" +
            "        \n" +
            "        .product_quantity_title {\n" +
            "            border-right: 1px solid white;\n" +
            "            width: 107px;\n" +
            "        }\n" +
            "        \n" +
            "        .product_price_title {\n" +
            "            width: 107px;\n" +
            "        }\n" +
            "        \n" +
            "        .item_container {\n" +
            "            padding-bottom: 5px !important;\n" +
            "            border-bottom: 2px solid #eeeeee !important;\n" +
            "        }\n" +
            "        \n" +
            "        .product_description {\n" +
            "            width: 330px !important;\n" +
            "            padding: 0px 5px 5px 0px;\n" +
            "        }\n" +
            "        \n" +
            "        .product_quantity_value {\n" +
            "            background-color: #f8f6f6;\n" +
            "            width: 107px !important;\n" +
            "        }\n" +
            "        \n" +
            "        .product_price_value {\n" +
            "            width: 107px !important;\n" +
            "        }\n" +
            "    </style>\n" +
            "</head>\n" +
            "\n" +
            "<body>\n" +
            "    <div class=\"container\">\n" +
            "        <table>\n" +
            "            <table class=\"header\">\n" +
            "                <tr>\n" +
            "                    <td>\n" +
            "                        <div class=\"logo\"></div>\n" +
            "                    </td>\n" +
            "                    <td> <a class=\"product_link_page\" href=\"/\">product page</a></td>\n" +
            "                </tr>\n" +
            "            </table>\n" +
            "\n" +
            "            <table class=\"section row\">\n" +
            "                <tr>\n" +
            "                    <td class=\"half\">\n" +
            "                        <p class=\"text_align_left label\">Замовлення</p>\n" +
            "                        <p class=\"order_id\">№PF-0000</p>\n" +
            "                    </td>\n" +
            "                    <td class=\"half\">\n" +
            "                        <p class=\"text_align_right label\">Сума замовлення</p>\n" +
            "                        <p class=\"order_id text_align_right\">0 грн.</p>\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "            </table>\n" +
            "\n" +
            "            <table class=\"section row\">\n" +
            "                <tr>\n" +
            "                    <td class=\"label text_align_left\" width=\"110px\">\n" +
            "                        <p>Замовник:</p>\n" +
            "                        <p>Телефон:</p>\n" +
            "                        <p>e-mail:</p>\n" +
            "                        <p>Дата:</p>\n" +
            "                    </td>\n" +
            "                    <td class=\"info text_align_left info\">\n" +
            "                        <p>Volodymyr Vlasov</p>\n" +
            "                        <p>+38 063 582 52 80</p>\n" +
            "                        <p>volodymyr.vlasov@gmail.com</p>\n" +
            "                        <p>23.02.2021 22:55</p>\n" +
            "                    </td>\n" +
            "                    <td>\n" +
            "                        <img class=\"delivery_method_logo\" src=\"https://paperfox.com.ua/wp-content/uploads/2021/02/np_logo.png\" alt=\"delivery_method_logo\">\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "            </table>\n" +
            "\n" +
            "\n" +
            "            <table class=\"row section  table_title\">\n" +
            "                <tr>\n" +
            "                    <td class=\"product_name_title label text_align_left\">\n" +
            "                        <p>Товар</p>\n" +
            "                    </td>\n" +
            "                    <td class=\"product_quantity_title label text_align_center\">\n" +
            "                        <p>Кількість</p>\n" +
            "                    </td>\n" +
            "                    <td class=\"product_price_title label text_align_center\">\n" +
            "                        <p>Вартість</p>\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "            </table>\n" +
            "\n" +
            "            <table class=\"row item_container\">\n" +
            "                <tr>\n" +
            "                    <td class=\"product_description text_align_left label\">\n" +
            "                        <!-- product description -->\n" +
            "                        <p class=\"label_small\">Звичайний друк</p>\n" +
            "                        <p class=\"info\">А3 - чорно-білий</p>\n" +
            "                        <p class=\"label_small\">Файли для друку:</p>\n" +
            "                        <p class=\"label_file_list\">file 1: Файл для печати.txt</p>\n" +
            "                        <p class=\"label_file_list\">file 2: 1151690054_202102091527_info_inc.pdf</p>\n" +
            "                    </td>\n" +
            "                    <td class=\"product_quantity_value label\">\n" +
            "                        <!-- quantity -->\n" +
            "                        <p>null</p>\n" +
            "                    </td>\n" +
            "                    <td class=\"product_price_value label\">\n" +
            "                        <!-- price -->\n" +
            "                        <p>null</p>\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "\n" +
            "            </table>\n" +
            "            <table class=\"row item_container\">\n" +
            "                <tr>\n" +
            "                    <td class=\"product_description text_align_left label\">\n" +
            "                        <!-- product description -->\n" +
            "                        <p class=\"label_small\">Звичайний друк</p>\n" +
            "                        <p class=\"info\">А3 - чорно-білий</p>\n" +
            "                        <p class=\"label_small\">Файли для друку:</p>\n" +
            "                        <p class=\"label_file_list\">file 1: Файл для печати.txt</p>\n" +
            "                        <p class=\"label_file_list\">file 2: 1151690054_202102091527_info_inc.pdf</p>\n" +
            "                    </td>\n" +
            "                    <td class=\"product_quantity_value label\">\n" +
            "                        <!-- quantity -->\n" +
            "                        <p>null</p>\n" +
            "                    </td>\n" +
            "                    <td class=\"product_price_value label\">\n" +
            "                        <!-- price -->\n" +
            "                        <p>null</p>\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "\n" +
            "            </table>\n" +
            "\n" +
            "        </table>\n" +
            "    </div>\n" +
            "</body>\n" +
            "\n" +
            "</html>";
}
