package com.sg.capstone.service;

import com.sg.capstone.dao.PostsDao;
import com.sg.capstone.dao.StaticPageDao;
import com.sg.capstone.models.Content;
import com.sg.capstone.models.Posts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class PostsServiceImpl implements PostsService{

    private static long daysBetween(Calendar from, Calendar to) {
        return ChronoUnit.DAYS.between(from.toInstant(), to.toInstant());
    }

    public static java.util.Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy/MM/dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    @Autowired
    PostsDao dao;

    @Override
    public void GetContent(Content c) {
        Posts p = new Posts();
        p.setImageURL(c.getImageURL());
        p.setTitle(c.getTitle());
        p.setPost(c.getPost());
        dao.addPost(p);
    }

    @Override
    @Transactional
    public void deletePostsById(int id) throws PostException{
        if (dao.getPostById(id) == null){
            throw new PostException("ERROR: Post doesn t exist!");
        }
        dao.deletePostsById(id);
    }

    @Override
    public List<Posts> getAllPosts(Date dt) {

        //The date needs to have this format yyyy/MM/dd
        java.util.Date myDate = parseDate(dt.toString());
        Calendar cal = Calendar.getInstance();
        cal.setTime(myDate);

        java.util.Date dateNow = new java.util.Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateNow);
        calendar.get(Calendar.DAY_OF_MONTH);

        List<Posts> allPosts = dao.getAllPosts();
        List<Posts> upToDatePosts = new ArrayList<>();
        for(Posts post : allPosts){
            if(daysBetween(cal, calendar) > 31){
                upToDatePosts.add(post);
            }
        }
        return upToDatePosts;
    }


}
