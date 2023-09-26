package vn.aptech.powerofspeed.repository.blog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.aptech.powerofspeed.model.blog.Blog;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {

}
