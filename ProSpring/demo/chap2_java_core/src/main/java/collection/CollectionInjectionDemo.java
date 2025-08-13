package collection;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CollectionInjectionDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(CollectionConfig.class, CollectingBean.class);
        ctx.refresh();

        CollectingBean collectingBean = ctx.getBean(CollectingBean.class);
        collectingBean.printCollections();
    }
}
