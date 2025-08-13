package collection;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CollectingBean {

    @Autowired @Qualifier("list")
    List<Song> songList;

    public void printCollections() {
        songList.forEach(s -> System.out.println(s.getTitle()));
    }
}
