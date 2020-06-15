package com.example.mvvmapp.viewmodel;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import com.blankj.ALog;
import com.example.commlib.base.mvvm.BaseViewModel;
import com.example.commlib.utils.GsonUtil;
import com.example.commlib.utils.ToastUtils;
import com.example.mvvmapp.bean.ArticleBean;
import com.example.mvvmapp.db.ArticleDaoUtil;

import java.util.List;

/**
 * Created by yzh on 2020/6/9 15:08.
 */
public class GreenDaoViewModel extends BaseViewModel
{
    public ObservableField<String> mContent=new ObservableField<>();

    ArticleDaoUtil daoUtil;
    public GreenDaoViewModel(@NonNull Application application) {
        super(application);
        daoUtil=new ArticleDaoUtil();
    }

    @Override
    public void onBundle(Bundle bundle) {

    }

    private String json="{\n" +
            "     \"articleId\": \"5eb3d5735e15a63ac692ef21\",\n" +
            "     \"collected\": true,\n" +
            "     \"contentType\": \"cnViewChapter\",\n" +
            "     \"keyWords\": [\n" +
            "         \"上\",\n" +
            "         \"下\",\n" +
            "         \"小\"\n" +
            "     ],\n" +
            "     \"level\": \"2\",\n" +
            "     \"paragraphList\": [\n" +
            "         {\n" +
            "             \"coverPosition\": \"up\",\n" +
            "             \"sentenceList\": [\n" +
            "                 {\n" +
            "                     \"content\": \"章节书书1\",\n" +
            "                     \"sentenceId\": \"0\",\n" +
            "                     \"voice\": \"http:\\/\\/test.pub.muyuhuajiaoyu.com\\/1094799970789236736.m3u8\",\n" +
            "                     \"voiceDesc\": \"\",\n" +
            "                     \"voiceOver\": \"\"\n" +
            "                 }\n" +
            "             ]\n" +
            "         },\n" +
            "         {\n" +
            "             \"coverPosition\": \"down\",\n" +
            "             \"sentenceList\": [\n" +
            "                 {\n" +
            "                     \"content\": \"小熊维尼——小熊维尼去做客和去打猎（四）\",\n" +
            "                     \"sentenceId\": \"1\",\n" +
            "                     \"voice\": \"http:\\/\\/test.pub.muyuhuajiaoyu.com\\/1094799970789236736.m3u8\",\n" +
            "                     \"voiceDesc\": \"\",\n" +
            "                     \"voiceOver\": \"\"\n" +
            "                 }\n" +
            "             ]\n" +
            "         },\n" +
            "         {\n" +
            "             \"coverPosition\": \"down\",\n" +
            "             \"sentenceList\": [\n" +
            "                 {\n" +
            "                     \"content\": \"突然，维尼停下来，非常兴奋地指着前面，说：“看！”\",\n" +
            "                     \"sentenceId\": \"2\",\n" +
            "                     \"voice\": \"http:\\/\\/test.pub.muyuhuajiaoyu.com\\/1094799970789236736.m3u8\",\n" +
            "                     \"voiceDesc\": \"\",\n" +
            "                     \"voiceOver\": \"\"\n" +
            "                 }\n" +
            "             ]\n" +
            "         },\n" +
            "         {\n" +
            "             \"coverPosition\": \"down\",\n" +
            "             \"sentenceList\": [\n" +
            "                 {\n" +
            "                     \"content\": \"“什么啊？”小猪皮杰跳了起来，为了表明自己刚才并不是被吓到了，他又像做锻炼似的上下跳了两次。\",\n" +
            "                     \"sentenceId\": \"3\",\n" +
            "                     \"voice\": \"http:\\/\\/test.pub.muyuhuajiaoyu.com\\/1094799970789236736.m3u8\",\n" +
            "                     \"voiceDesc\": \"\",\n" +
            "                     \"voiceOver\": \"\"\n" +
            "                 }\n" +
            "             ]\n" +
            "         }\n" +
            "     ],\n" +
            "     \"recordInterval\": 0,\n" +
            "     \"rewardCredits\": 5,\n" +
            "     \"subject\": \"EN\",\n" +
            "     \"title\": \"章节书书1\",\n" +
            "     \"totalCredits\": 318857\n" +
            " }";
    public MutableLiveData<List<ArticleBean>> getTestData() {

        final MutableLiveData<List<ArticleBean>> data = new MutableLiveData<>();
        data.setValue(daoUtil.queryAllArticleBean());
        return data;
    }

    /**
     * 更新显示
     */
    public void updateContent(){
        List<ArticleBean> beanList=daoUtil.queryAllArticleBean();
        StringBuilder builder=new StringBuilder();
        for (ArticleBean bean:beanList){
            ALog.i(GsonUtil.getBeanToJson(bean));
            builder.append(GsonUtil.getBeanToJson(bean)).append("\n").append("\n");

        }

        mContent.set(builder.toString());

    }


    public void add(){
        ArticleBean bean= GsonUtil.parseJsonToBean(json,ArticleBean.class);
        daoUtil.insertArticleBean(bean);
        updateContent();
    }

    public void deleteAll(){
        daoUtil.deleteAll();
        updateContent();
    }


    public void update(int id){
        ArticleBean bean= daoUtil.queryArticleBeanById(id);
        if(bean==null){
            ToastUtils.showShort(id+" 不存在");
            return;
        }
        daoUtil.updateArticleBean(bean);
        updateContent();
    }

    public void delete(int id){
        ArticleBean bean= daoUtil.queryArticleBeanById(id);
        if(bean==null){
            ToastUtils.showShort(id+" 不存在");
            return;
        }
        daoUtil.deleteArticleBean(bean);
        updateContent();
    }
}
