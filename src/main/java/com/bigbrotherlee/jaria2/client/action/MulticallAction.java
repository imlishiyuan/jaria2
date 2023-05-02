package com.bigbrotherlee.jaria2.client.action;

import com.alibaba.fastjson.JSON;
import com.bigbrotherlee.jaria2.client.enums.ActionEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * MulticallAction ,this action is Deprecated, please use not single call action instead.
 *
 * why Deprecated? because this action`s response is not a good Object, it`s not easy to use.
 *
 * @author lee
 */
@Deprecated
public class MulticallAction extends BaseAction<MulticallAction.MulticallActionResponse>{
    private static final ActionEnum ACTION_ENUM = ActionEnum.MULTI_CALL;

    private transient final List<MulticallUnit> multicallUnitList = new ArrayList<>();

    public MulticallAction(String id){
        super(ACTION_ENUM.name,id);
        getParams().add(multicallUnitList);
    }


    public <A extends BaseAction> void addMulticallUnit(A action){
        MulticallUnit multicallUnit = new MulticallUnit();
        multicallUnit.setMethodName(action.getMethod());
        multicallUnit.getParams().addAll(action.getParams());
        multicallUnitList.add(multicallUnit);
    }

    @Override
    public void setToken(String token) {
        String allToken = TOKEN_PREFIX + token;
        multicallUnitList.forEach(multicallUnit -> multicallUnit.getParams().add(0,allToken));
    }

    @Override
    public MulticallActionResponse buildRespFromStr(String content) {
        return JSON.parseObject(content,MulticallActionResponse.class);
    }

    /**
     * 定义rpc请求的响应
     */
    public static class MulticallActionResponse extends ActionResponse<List<Object>>{}


    @Setter
    @Getter
    public static class MulticallUnit{
        private String methodName;
        private List<Object> params = new ArrayList<>();
    }


}
