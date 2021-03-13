package services.mailServices;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import models.bots.CustomTelegramBot;
import models.shop.Order;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class EmailTemplator {
    private static final String emailTmplPath = "./template/test.tml";
    private static EmailTemplator instance;
    private Configuration tmplCfg;

    private EmailTemplator() {
        tmplCfg = new Configuration(new Version("2.3.23"));
        tmplCfg.setDefaultEncoding("UTF-8");
        tmplCfg.setClassForTemplateLoading(MailService.class, "/");
    }

    public static EmailTemplator getInstance() {
        if (instance == null) instance = new EmailTemplator();
        return instance;
    }

    public String getHTMLMsg(Order order, String title) throws IOException, TemplateException {
        Map<String, Object> tmplData = new HashMap<String, Object>();
        tmplData.put("order", order);
        tmplData.put("title", title);

        Template emailTmp = this.tmplCfg.getTemplate(emailTmplPath);

        Writer w = new StringWriter();
        emailTmp.process(tmplData, w);
        return w.toString();
    }
}
