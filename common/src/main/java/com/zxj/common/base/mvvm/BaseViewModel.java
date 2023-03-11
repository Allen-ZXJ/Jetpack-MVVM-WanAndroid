package com.zxj.common.base.mvvm;


import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;

public class BaseViewModel<M extends BaseModel> extends ViewModel implements IBaseViewModel {
    public M model;

    public BaseViewModel() {
        model = creatModel();
    }

    private M creatModel() {
        try {
            Type type = getClass().getGenericSuperclass();
            Type actualType = ((ParameterizedType) type).getActualTypeArguments()[0];
            Class<?> clazz = getRawType(actualType);
            return (M)clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Type：
     * Class，普通类型
     * ParameterizedType：泛型类，例如List<String>等，getActualTypeArguments()获取<>里面参数的类型，返回的是一个Type[]
     * GenericArrayType： 泛型类数组，例如List<String>[]， getGenericComponentType()获取 去掉[]后的类型，前面去掉[]就是List<String>
     * WildcardType： 通配符类型， 例如 ? extends xx, 有getLowerBounds获取下边界的类型，getUppersBounds获取上边界的类型
     * TypeVariable： 未确认类型的类型， 例如 T,M等指代泛型的
     *
     * @param type
     * @return
     */

    // type不能直接实例化对象，通过type获取class的类型，然后实例化对象
    private Class<?> getRawType(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        } else if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type rawType = parameterizedType.getRawType();
            return (Class) rawType;
        } else if (type instanceof GenericArrayType) {
            Type componentType = ((GenericArrayType) type).getGenericComponentType();
            return Array.newInstance(getRawType(componentType), 0).getClass(); //创建一个xx类型的数组
        } else if (type instanceof TypeVariable) {
            return Object.class;
        } else if (type instanceof WildcardType) {
            return getRawType(((WildcardType) type).getUpperBounds()[0]);
        } else {
            String className = type == null ? "null" : type.getClass().getName();
            throw new IllegalArgumentException("Expected a Class, ParameterizedType, or GenericArrayType, but <" + type + "> is of type " + className);
        }
    }

    @Override
    protected void onCleared() {
        if(model != null){
            model.onCleared();
        }
    }

    @Override
    public void onAny(LifecycleOwner owner, Lifecycle.Event event) {

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }
}
