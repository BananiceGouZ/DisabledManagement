//package cn.bananice;
//
//
//import cn.bananice.pet.domain.Pet;
//import cn.bananice.pet.service.IPetService;
//import freemarker.template.Configuration;
//import freemarker.template.Template;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
//
//import java.io.File;
//import java.io.FileWriter;
//
//public class FreemarkTest extends BaseTest {
//
//    @Autowired
//    private FreeMarkerConfigurer freeMarkerConfigurer;
//
//    @Value("${pagedir}")
//    private String path;
//
//    @Autowired
//    private IPetService petService;
//
//    @Test
//    public void testHtml() throws Exception {
//        //创建模板技术的核心配置对象
//        Configuration configuration = freeMarkerConfigurer.getConfiguration();
//        //configuration.setClassForTemplateLoading(this.getClass(), "templates/");
//        //ContextLoader loader = new ContextLoader();
//        Template template = configuration.getTemplate("pet_detail.ftl");
//        //准备数据
//        Pet pet = petService.queryById(30L);
//
//        //合并模板
//        template.process(pet, new FileWriter(new File(path + "\\" + pet.getId() + ".html")));
//    }
//}