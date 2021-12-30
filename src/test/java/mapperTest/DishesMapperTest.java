//package mapperTest;
//
//import com.alibaba.fastjson.JSON;
//import com.mapper.DishesMapper;
//import com.pojo.Dishes;
//import com.util.SqlSessionFactoryUtils;
//import junit.framework.TestCase;
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//
//import java.util.List;
//
//public class DishesMapperTest extends TestCase {
//
////    public void testSelectDishes() {
//        // 创建SqlSessionFactory工厂
//        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
//        // 创建连接
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        // 创建映射关系
//        DishesMapper dishesMapper = sqlSession.getMapper(DishesMapper.class);
//        // 执行sql
//        List<Dishes> dishesList = dishesMapper.selectDishesByStoreId(1);
//        // 将dishesList转换为json
//        String dishesListJson = JSON.toJSONString(dishesList);
//
//        System.out.println(dishesListJson);
//        // 关闭连接
//        sqlSession.close();
//    }
//}