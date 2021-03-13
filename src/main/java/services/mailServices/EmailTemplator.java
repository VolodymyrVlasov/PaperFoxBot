package services.mailServices;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import models.bots.CustomTelegramBot;
import models.shop.Order;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class EmailTemplator {
    private static final String emailTmplPath = "template.ftl"; // src/main/java/template
    private static EmailTemplator instance;
    private Configuration tmplCfg;

    private EmailTemplator() throws IOException {
        tmplCfg = new Configuration(new Version("2.3.23"));
        tmplCfg.setDefaultEncoding("UTF-8");
       // tmplCfg.setClassForTemplateLoading(EmailTemplator.class, "/");
        tmplCfg.setDirectoryForTemplateLoading(new File("C:\\Users\\User\\IdeaProjects\\PaperFoxBot\\src\\main\\java\\services\\mailServices\\template"));
        tmplCfg.getTemplate(emailTmplPath);
    }

    public static EmailTemplator getInstance() throws IOException {
        if (instance == null) instance = new EmailTemplator();
        return instance;
    }

    public String getHTMLMsg(Order order, String title) throws IOException, TemplateException {
        Map<String, Object> tmplData = new HashMap<String, Object>();
        tmplData.put("orderId", order.getOrderId());
        tmplData.put("title", title);
        tmplData.put("title", title);
        tmplData.put("orders", new String[]{"order1", "order2"});

        Template emailTmp = this.tmplCfg.getTemplate(emailTmplPath);

        Writer w = new StringWriter();
        emailTmp.process(tmplData, w);
        return w.toString();
    }
}
