package com.bb.hbx.api;

import com.bb.hbx.bean.Account;
import com.bb.hbx.bean.ActivitInfo;
import com.bb.hbx.bean.AddConsignee;
import com.bb.hbx.bean.AddInsured;
import com.bb.hbx.bean.AreasListBean;
import com.bb.hbx.bean.BannerBean;
import com.bb.hbx.bean.BindingBankCard;
import com.bb.hbx.bean.BobaoItem;
import com.bb.hbx.bean.CollectionBean;
import com.bb.hbx.bean.Consignees;
import com.bb.hbx.bean.DeleteConsignee;
import com.bb.hbx.bean.FilterBean;
import com.bb.hbx.bean.GetAccountDetailBean;
import com.bb.hbx.bean.GetAcctSettSumBean;
import com.bb.hbx.bean.GetBankCardList;
import com.bb.hbx.bean.GetInsured;
import com.bb.hbx.bean.GetMyPageInfoBean;
import com.bb.hbx.bean.GetPolicies;
import com.bb.hbx.bean.GetTotalIncomeBean;
import com.bb.hbx.bean.GetTotalIncomeDetail;
import com.bb.hbx.bean.GetTradesBean;
import com.bb.hbx.bean.GetUserCouponsListBean;
import com.bb.hbx.bean.HasUsedPreInsuBean;
import com.bb.hbx.bean.HomePageInfo;
import com.bb.hbx.bean.Hotkey;
import com.bb.hbx.bean.IniviteFriendsBean;
import com.bb.hbx.bean.MessageCodeBean;
import com.bb.hbx.bean.MsgInfo;
import com.bb.hbx.bean.OssBean;
import com.bb.hbx.bean.Output_AreaBean;
import com.bb.hbx.bean.PayDetail;
import com.bb.hbx.bean.PaySign;
import com.bb.hbx.bean.PresentInsuBean;
import com.bb.hbx.bean.ProdectDetalRequest;
import com.bb.hbx.bean.ProductBean;
import com.bb.hbx.bean.ProductItem;
import com.bb.hbx.bean.ProductParamDetail;
import com.bb.hbx.bean.RecommendBean;
import com.bb.hbx.bean.RequestProduct;
import com.bb.hbx.bean.SingleCustomBean;
import com.bb.hbx.bean.SingleCustomEditBean;
import com.bb.hbx.bean.Special;
import com.bb.hbx.bean.TopicBean;
import com.bb.hbx.bean.TradeDetail;
import com.bb.hbx.bean.TypeModel;
import com.bb.hbx.bean.UpdateConsignee;
import com.bb.hbx.bean.UpdateInsured;
import com.bb.hbx.bean.User;
import com.bb.hbx.bean.UserInfo;
import com.bb.hbx.bean.UserRegist;
import com.bb.hbx.bean.VersionInfo;
import com.bb.hbx.pay.llianlianpay.utils.PayOrder;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by fancl.
 * 服务器接口
 */

public interface ApiService {


    //获取验证码
    @FormUrlEncoded
    @POST("api.do?method=getVerifyCode&type=post")
    Call<Result_Api<MessageCodeBean>> getVerifyCode(@Field("codeType") String codeType
            , @Field("mobile") String mobile, @Field("bizType") String bizType);

    //验证码校验
    @FormUrlEncoded
    @POST("api.do?method=verifyCode&type=post")
    Call<Result_Api> verifyCode(@Field("codeType") String codeType
            , @Field("verifyCode") String verifyCode, @Field("bizType") String bizType);

    //用户注册
    @FormUrlEncoded
    @POST("api.do?method=regUser&type=post")
    Call<Result_Api<UserRegist>> regUser(@Field("userName") String userName
            , @Field("loginPwd") String loginPwd, @Field("smsCode") String smsCode);


    //短信登录
    @FormUrlEncoded
    @POST("api.do?method=login&type=post")
    Call<Result_Api<User>> loginSms(@Field("userName") String userName, @Field("smsCode") String smsCode, @Field("loginType") String loginType);

    //密码登录
    @FormUrlEncoded
    @POST("api.do?method=login&type=post")
    Call<Result_Api<User>> login(@Field("userName") String userName, @Field("loginPwd") String loginPwd, @Field("loginType") String loginType);

    //忘记密码------已测
    @FormUrlEncoded
    @POST("api.do?method=forgetLoginPwd&type=post")
    Call<Result_Api> forgetLoginPwd(@Field("mobile") String mobile, @Field("newPwd") String newPwd, @Field("resetType") String resetType, @Field("smsCode") String smsCode);

    //重设登录密码--已测  已经注册过登录密码
    @FormUrlEncoded
    @POST("api.do?method=resetLoginPwd&type=post")
    Call<Result_Api> resetLoginPwd(@Field("userId") String userId, @Field("newPwd") String newPwd,
                                   @Field("resetType") String resetType, @Field("smsCode") String smsCode);

    //重设登录密码--已测  未注册过登录密码
    @FormUrlEncoded
    @POST("api.do?method=resetLoginPwd&type=post")
    Call<Result_Api> resetLoginPwdFirst(@Field("userId") String userId, @Field("newPwd") String newPwd, @Field("resetType") String resetType);

    //登录注销
    @FormUrlEncoded
    @POST("api.do?method=logout&type=post")
    Call<Result_Api> logout(@Field("userId") String userId);

    //我的首页
    @FormUrlEncoded
    @POST("api.do?method=getUserInfo&type=post")
    Call<Result_Api<UserInfo>> getUserInfo(@Header("sessionId") String sessionId, @Field("userId") String userId);

    //查询我的首页的账户信息
    @FormUrlEncoded
    @POST("api.do?method=getMyPageInfo&type=post")
    Call<Result_Api<GetMyPageInfoBean>> getMyPageInfo(@Field("userId") String userId);

    //查询账户信息
    @FormUrlEncoded
    @POST("api.do?method=getAccount&type=post")
    Call<Result_Api<Account>> getAccount(@Field("userId") String userId, @Field("acctType") String acctType);

    //查询资金(积分)变动明细()--已测
    @FormUrlEncoded
    @POST("api.do?method=getAccountDetail&type=post")
    Call<Result_Api<GetAccountDetailBean>> getAccountDetail(@Field("userId") String userId,
                                                            @Field("acctType") String acctType,
                                                            @Field("pageIndex") String pageIndex,
                                                            @Field("tradeType") String tradeType);

    //积分签到--待测
    @FormUrlEncoded
    @POST("api.do?method=checkIn&type=post")
    Call<String> checkIn(@Field("userId") String userId, @Field("acctType") String acctType);

    //累计收入
    @FormUrlEncoded
    @POST("api.do?method=getTotalIncome&type=post")
    Call<Result_Api<GetTotalIncomeBean>> getTotalIncome(@Field("userId") String userId, @Field("acctType") String acctType,
                                                        @Field("beginDate") String beginDate, @Field("endDate") String endDate,
                                                        @Field("pageIndex") String pageIndex, @Field("pageSize") String pageSize);

    //收入月账单--已测
    @FormUrlEncoded
    @POST("api.do?method=getTotalIncomeDetail&type=post")
    Call<Result_Api<GetTotalIncomeDetail>> getTotalIncomeDetail(@Field("userId") String userId, @Field("acctType") String acctType,
                                                                @Field("beginDate") String beginDate, @Field("endDate") String endDate,
                                                                @Field("pageIndex") String pageIndex, @Field("pageSize") String pageSize);

    //结算中金额明细列表--已测
    @FormUrlEncoded
    @POST("api.do?method=getAcctSettSumList&type=post")
    Call<Result_Api<GetAcctSettSumBean>> getAcctSettSumList(@Field("userId") String userId, @Field("acctType") String acctType,
                                                            @Field("beginDate") String beginDate, @Field("endDate") String endDate,
                                                            @Field("pageIndex") String pageIndex, @Field("pageSize") String pageSize,
                                                            @Field("commisionSts") String commisionSts);

    //修改用户信息,nickName
    @FormUrlEncoded
    @POST("api.do?method=updateUserInfo&type=post")
    Call<Result_Api> updateUserInfo(@Field("userId") String userId, @Field("nickName") String nickName);

    //修改用户信息,性别
    @FormUrlEncoded
    @POST("api.do?method=updateUserInfo&type=post")
    Call<Result_Api> updateUserInfoSex(@Field("userId") String userId, @Field("gender") String gender);

    //修改用户信息,邮箱地址
    @FormUrlEncoded
    @POST("api.do?method=updateUserInfo&type=post")
    Call<Result_Api> updateUserInfoEmail(@Field("userId") String userId, @Field("email") String email);

    //获取收货人信息
    @FormUrlEncoded
    @POST("api.do?method=getConsignees&type=post")
    Call<Result_Api<Consignees>> getConsignees(@Field("userId") String userId, @Field("pageIndex") String pageIndex, @Field("pageSize") String pageSize);

    //修改收货人信息
    @FormUrlEncoded
    @POST("api.do?method=updateConsignee&type=post")
    Call<Result_Api<UpdateConsignee>> updateConsignee(@Field("userId") String userId, @Field("cneeId") String cneeId, @Field("cneeName") String cneeName,
                                                      @Field("mobile") String mobile, @Field("areaCode") String areaCode, @Field("address") String address,
                                                      @Field("defaultFlag") String defaultFlag);

    //新增收货人信息
    @FormUrlEncoded
    @POST("api.do?method=addConsignee&type=post")
    Call<Result_Api<AddConsignee>> addConsignee(@Field("userId") String userId, @Field("cneeName") String cneeName, @Field("mobile") String mobile,
                                                @Field("areaCode") String areaCode, @Field("areaId") String areaId, @Field("address") String address,
                                                @Field("syncUser") String syncUser, @Field("defaultFlag") String defaultFlag);

    //删除收货人信息
    @FormUrlEncoded
    @POST("api.do?method=delConsignee&type=post")
    Call<Result_Api<DeleteConsignee>> delConsignee(@Field("userId") String userId, @Field("cneeId") String cneeId);

    //获取地区信息
    @FormUrlEncoded
    @POST("api.do?method=getAreaList&type=post")
    Call<Result_Api<AreasListBean>> getAreaList(@Field("findAllFlag") boolean findAllFlag);

    //获取用户银行卡信息
    @FormUrlEncoded
    @POST("api.do?method=getBankCardList&type=post")
    Call<Result_Api<GetBankCardList>> getBankCardList(@Field("userId") String userId);

    //添加银行卡
    @FormUrlEncoded
    @POST("api.do?method=bindingBankCard&type=post")
    Call<Result_Api<BindingBankCard>> bindingBankCard(@Field("userId") String userId, @Field("accountName") String accountName, @Field("idNo") String idNo,
                                                      @Field("cardNo") String cardNo/*, @Field("bankName") String bankName*/);

    //解绑银行卡
    @FormUrlEncoded
    @POST("api.do?method=releaseBankCard&type=post")
    Call<Result_Api> releaseBankCard(@Field("userId") String userId);

    //提现--待测
    @FormUrlEncoded
    @POST("api.do?method=applyCash&type=post")
    Call<Result_Api> applyCash(@Field("userId") String userId, @Field("cashAmount") String cashAmount);

    //修改支付密码
    @FormUrlEncoded
    @POST("api.do?method=modPayPwd&type=post")
    Call<Result_Api> modPayPwd(@Field("userId") String userId, @Field("oldPayPwd") String oldPayPwd, @Field("newPayPwd") String newPayPwd);


    //忘记支付密码
    @FormUrlEncoded
    @POST("api.do?method=forgetPayPwd&type=post")
    Call<Result_Api> forgetPayPwd(@Field("userId") String userId, @Field("payPwd") String payPwd, @Field("smsCode") String smsCode);

    //设置支付密码--已测
    @FormUrlEncoded
    @POST("api.do?method=setPayPwd&type=post")
    Call<Result_Api> setPayPwd(@Field("userId") String userId, @Field("payPwd") String payPwd, @Field("smsCode") String smsCode);


    //上传用户头像,阿里云
    @FormUrlEncoded
    @POST("api.do?method=getOssToken&type=post")
    Call<Result_Api<OssBean>> getOssToken(@Field("userId") String userId);

    //获取常用保险联系人--已测,接口返回数据待修改
    @FormUrlEncoded
    @POST("api.do?method=getInsured&type=post")
    Call<Result_Api<GetInsured>> getInsured(@Field("userId") String userId, @Field("mobile") String mobile,
                                            @Field("pageIndex") String pageIndex, @Field("pageSize") String pageSize);

    /*//新增常用保险联系人--已测,
    @FormUrlEncoded
    @POST("api.do?method=addInsured&type=post")
    Call<Result_Api<AddInsured>> addInsured(@Field("userId") String userId, @Field("insuredName") String insuredName, @Field("mobile") String mobile,
                                            @Field("idType") String idType, @Field("idNo") String idNo);*/

    //新增常用保险联系人--已测,

    @POST("api.do?method=addInsured&type=post")
    Call<Result_Api<AddInsured>> addInsured(@Body SingleCustomBean bean);

    /*//修改常用保险联系人--已测
    @FormUrlEncoded
    @POST("api.do?method=updateInsured&type=post")
    Call<Result_Api<UpdateInsured>> updateInsured(@Field("userId") String userId, @Field("insuredId") String insuredId, @Field("insuredName") String insuredName,
                                                  @Field("mobile") String mobile, @Field("idType") String idType, @Field("idNo") String idNo);*/

    //修改常用保险联系人--已测
    @POST("api.do?method=updateInsured&type=post")
    Call<Result_Api<UpdateInsured>> updateInsured(@Body SingleCustomEditBean beanEdit);

    //删除常用保险联系人--已测
    @FormUrlEncoded
    @POST("api.do?method=delInsured&type=post")
    Call<Result_Api<UpdateInsured>> delInsured(@Field("userId") String userId, @Field("insuredId") String insuredId);

    //获取我的订单列表--已测
    @FormUrlEncoded
    @POST("api.do?method=getTrades&type=post")
    Call<Result_Api<GetTradesBean>> getTrades(@Field("userId") String userId, @Field("tradeSts") String tradeSts,
                                              @Field("pageIndex") String pageIndex, @Field("pageSize") String pageSize);

    //获取我的保单列表--已测
    @FormUrlEncoded
    @POST("api.do?method=getPolicies&type=post")
    Call<Result_Api<GetPolicies>> getPolicies(@Field("userId") String userId, @Field("policySts") String policySts, @Field("classType") String classType,
                                              @Field("pageIndex") String pageIndex, @Field("pageSize") String pageSize);

    /*//查看订单(保单)详情--待测
    @FormUrlEncoded
    @POST("api.do?method=getTradeDetail&type=post")
    Call<Result_Api> getTradeDetail(@Field("userId") String userId,@Field("classType") String classType);*/


    //获取我的红包记录列表--已测
    @FormUrlEncoded
    @POST("api.do?method=getUserCouponsList&type=post")
    Call<Result_Api<GetUserCouponsListBean>> getUserCouponsList(@Field("userId") String userId,
                                                                @Field("sts") String sts,
                                                                @Field("couponType") String couponType,
                                                                @Field("pageIndex") String pageIndex,
                                                                @Field("pageSize") String pageSize);

    //判断用户是否能升B--待测
    @FormUrlEncoded
    @POST("api.do?method=hasUpgradeRight&type=post")
    Call<Result_Api<String>> hasUpgradeRight(@Field("userId") String userId);


    //获取首页数据
    @FormUrlEncoded
    @POST("api.do?method=getHomePageInfo&type=post")
    Call<Result_Api<HomePageInfo>> getHomePageInfo(@Field("userId") String userId);


    //获取保险分类列表
    @FormUrlEncoded
    @POST("api.do?method=getProductType&type=post")
    Call<Result_Api<TypeModel>> getProductType(@Field("parentId") String parentId);


    //获取系统搜索热词
    @FormUrlEncoded
    @POST("api.do?method=getHotKeys&type=post")
    Call<Result_Api<Hotkey>> getHotKeys(@Field("pageIndex") int pageIndex, @Field("pageSize") int pageSize);

    //获取产品列表

    @POST("api.do?method=getProducts&type=post")
    Call<Result_Api<ProductBean>> getProducts(@Body RequestProduct product);


    //获取用户消息列表
    @FormUrlEncoded
    @POST("api.do?method=getMsgs&type=post")
    Call<Result_Api<MsgInfo>> getMsgsUser(@Field("userId") String userId, @Field("msgType") String msgType,
                                          @Field("sts") String sts, @Field("pageIndex") String pageIndex);

    //阅读消息
    @FormUrlEncoded
    @POST("api.do?method=readMsg&type=post")
    Call<Result_Api> readMsg(@Field("userId") String userId, @Field("msgType") String msgType, @Field("msgId") String msgId);

    //获取消息列表
    @FormUrlEncoded
    @POST("api.do?method=getMsgs&type=post")
    Call<Result_Api<MsgInfo>> getMsgs(@Field("userId") String userId, @Field("msgType") String msgType,
                                      @Field("sts") String sts, @Field("pageIndex") int pageIndex, @Field("pageSize") int pageSize);

    //获取产品详情
    @FormUrlEncoded
    @POST("api.do?method=getProductDetail&type=post")
    Call<Result_Api<ProductParamDetail>> getProductDetail(@Field("productId") String productId);

    //获取专题列表
    @FormUrlEncoded
    @POST("api.do?method=getSpecials&type=post")
    Call<Result_Api<TopicBean>> getSpecials(@Field("pageIndex") int pageIndex);


    //版本信息获取
    @FormUrlEncoded
    @POST("api.do?method=getClientCtlInfo&type=post")
    Call<Result_Api<VersionInfo>> getClientCtlInfo(@Field("userId") String userId);

    //版本信息获取
    @FormUrlEncoded
    @POST("api.do?method=getStartUpImgs&type=post")
    Call<Result_Api<String>> getStartUpImgs();


    //获取Banner

    @POST("api.do?method=getBannerInfo&type=post")
    Call<Result_Api<BannerBean>> getBannerInfo();


    //获取小汇报消息列表
    @POST("api.do?method=getXhbMessageInfo&type=post")
    Call<Result_Api<BobaoItem>> getXhbMessageInfo();


    //获取首页分类

    @POST("api.do?method=getHomePageProductType&type=post")
    Call<Result_Api<ProductItem>> getHomePageProductType();

    //获取首页专题列表
    @POST("api.do?method=getHomePageProductList&type=post")
    Call<Result_Api<Special>> getHomePageProductList();

    //获取未读消息条数
    @FormUrlEncoded
    @POST("api.do?method=getUnReadMessageCount&type=post")
    Call<Result_Api<MsgInfo>> getUnReadMessageCount(@Field("userId") String userId);

    //获取未读消息条数
    @FormUrlEncoded
    @POST("api.do?method=getAdsList&type=post")
    Call<Result_Api<ActivitInfo>> getAdsList(@Field("pageIndex") int pageIndex, @Field("pageSize") int pageSize);


    //爆款推荐
    @FormUrlEncoded
    @POST("api.do?method=getSpecialProductList&type=post")
    Call<Result_Api<RecommendBean>> getSpecialProductList(@Field("pageIndex") int pageIndex);


    //提交订单
    @POST("api.do?method=applyTrade&type=post")
    Call<Result_Api<PayDetail>> applyTrade(@Body ProdectDetalRequest request);


    //获取保险公司列表
    @FormUrlEncoded
    @POST("api.do?method=getInsurers&type=post")
    Call<Result_Api<FilterBean>> getInsurers(@Field("parentId") String parentId, @Field("insurerId") String insurerId,
                                             @Field("insurerType") String insurerType);


    //查看订单（保单）详情(getTradeDetail)
    @FormUrlEncoded
    @POST("api.do?method=getTradeDetail&type=post")
    Call<Result_Api<TradeDetail>> getTradeDetail(@Field("userId") String userId, @Field("tradeId") String tradeId,
                                                 @Field("detailId") String detailId, @Field("classType") String classType);

    //6.1.6.获取支付签名信息

    @POST("api.do?method=getPaySign&type=post")
    Call<Result_Api<PaySign>> getPaySign(@Body PayDetail detail);


    //校验支付密码
    @FormUrlEncoded
    @POST("api.do?method=verifyPayPwd&type=post")
    Call<Result_Api> verifyPayPwd(@Field("userId") String userId, @Field("payPwd") String payPwd);

    //用户升B
    @FormUrlEncoded
    @POST("api.do?method=updateUpgradeB&type=post")
    Call<Result_Api> updateUpgradeB(@Field("userId") String userId);

    //7.1.2.	获取车险投保地区(getAvaCarAreas)
    @FormUrlEncoded
    @POST("api.do?method=getAvaCarAreas&type=post")
    Call<Result_Api<Output_AreaBean>> getAvaCarAreas(@Field("companyCode") String companyCode, @Field("areaCode") String areaCode);

    //邀请好友
    @FormUrlEncoded
    @POST("api.do?method=inviteFriend&type=post")
    Call<Result_Api<IniviteFriendsBean>> getInviteFriend(@Field("userId") String userId);

    //连连支付
    @POST("api.do?method=getPaySign&type=post")
    Call<Result_Api<PayOrder>> getPaymentInfo(@Body PayDetail detail);

    //修改手机号
    @FormUrlEncoded
    @POST("api.do?method=updateMobile&type=post")
    Call<Result_Api> updateMobile(@Field("userId") String userId,
                                  @Field("smsCode") String smsCode,
                                  @Field("loginPwd") String loginPwd,
                                  @Field("mobile") String mobile,
                                  @Field("smsCodeNew") String smsCodeNew);

    //修改手机号密码校验
    @FormUrlEncoded
    @POST("api.do?method=verifyPwd&type=post")
    Call<Result_Api> checkVerifyPwd(@Field("userId") String userId,@Field("loginPwd") String loginPwd);

    //赠险产品(可领取)
    @FormUrlEncoded
    @POST("api.do?method=presentProduct&type=post")
    Call<Result_Api<PresentInsuBean>> getPresentProduct(@Field("userId") String userId,
                                                              @Field("sts") String sts,
                                                              @Field("pageIndex") String pageIndex,
                                                              @Field("pageSize") String pageSize);

    //赠险产品(已使用)
    @FormUrlEncoded
    @POST("api.do?method=presentProduct&type=post")
    Call<Result_Api<HasUsedPreInsuBean>> getUsedPresentProduct(@Field("userId") String userId,
                                                               @Field("sts") String sts,
                                                               @Field("pageIndex") String pageIndex,
                                                               @Field("pageSize") String pageSize);

    //获取收藏列表
    @FormUrlEncoded
    @POST("api.do?method=userFavoritesDetail&type=post")
    Call<Result_Api<CollectionBean>> getUserFavoritesDetail(@Field("userId") String userId,
                                                            @Field("objType") String objType,
                                                            @Field("benefitNum") String benefitNum);

    //收藏或取消收藏商品
    @FormUrlEncoded
    @POST("api.do?method=userFavorites&type=post")
    Call<Result_Api> collectProduct(@Field("userId") String userId,
                                            @Field("objType") String objType,
                                            @Field("objId") String objId,
                                            @Field("objStatus") String objStatus);
}
