package com.example.orm_backend.serviceImpl;

import com.example.orm_backend.dao.UserDao;
import com.example.orm_backend.entity.BookWithNumber;
import com.example.orm_backend.entity.User;
import com.example.orm_backend.entity.UserComparator;
import com.example.orm_backend.entity.UserWithMoney;
import com.example.orm_backend.service.BookService;
import com.example.orm_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void register(String name, String password, String email, String address) {
        userDao.register(name, password, email, address);
    }

    @Override
    public String checkRepeat(String user_name) {
        List<User> user_all =  userDao.checkRepeat(user_name);
        List<User> user_by_name = new ArrayList<User>();

        for (User user : user_all) {
            if (Objects.equals(user.getUser_name(), user_name))
                user_by_name.add(user);
        }

        String res = "";

        if (user_by_name.isEmpty())
            res = "[\"NoRepeat\"]";
        else
            res = "[\"Repeat\"]";

        return res;
    }

    @Override
    public List<User> checkAuth(String name, String password, String identity) {
        return userDao.checkAuth(name, password, identity);
    }

    @Override
    public List<User> getUsersAll() {
        return userDao.getUsersAll();
    }

    @Override
    public void changeState(int user_id) {
        userDao.changeState(user_id);
    }

    @Autowired
    BookService bookService;

    @Override
    public List<UserWithMoney> getUsersRanking(String start, String end) throws ParseException {
        List<User> users_all = userDao.getUsersAll();
        List<UserWithMoney> users = new ArrayList<>();

        // 首先创建一个销售金额全部为零的用户列表，转移至前端开发
        for (User user: users_all) {
            UserWithMoney userWithMoney = new UserWithMoney(user.getId(), user.getUser_name(), user.getUser_password(), user.getIdentity(), user.getEmail(), user.getAddress(), user.getState());
            if (Objects.equals(user.getIdentity(), "customer"))
                users.add(userWithMoney);
        }

        // 利用在目标时间范围之内的订单，计算每一个用户的消费情况
        for (UserWithMoney userWithMoney: users) {
            List<BookWithNumber> user_books = bookService.getBooksInRange(userWithMoney.id, start, end);
            for (BookWithNumber bookWithNumber: user_books) {
                userWithMoney.money += bookWithNumber.number * bookWithNumber.price;
            }
        }

        // 依照消费情况将所有的用户进行排序
        users.sort(new UserComparator());

        return users;
    }
}
