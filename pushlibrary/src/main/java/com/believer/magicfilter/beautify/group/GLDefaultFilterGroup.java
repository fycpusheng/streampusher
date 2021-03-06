package com.believer.magicfilter.beautify.group;



import com.believer.magicfilter.beautify.filter.GLImageFilter;
import com.believer.magicfilter.beautify.filter.GLRealtimeBeautyFilter;
import com.believer.magicfilter.beautify.manager.FilterManager;
import com.believer.magicfilter.beautify.utils.GLFilterIndex;
import com.believer.magicfilter.beautify.utils.type.GLFilterType;

import java.util.ArrayList;
import java.util.List;

/**
 * 默认实时美颜滤镜组
 * Created by cain on 2017/7/30.
 */
public class GLDefaultFilterGroup extends GLImageFilterGroup {
    // 实时美颜层
    private static final int BeautyfyIndex = 0;
    // 实时美颜层
    private static final int WhiteOrRed = 1;
    // 颜色层
    private static final int ColorIndex = 1;
    // 瘦脸大眼层
    private static final int FaceStretchIndex = 2;
    // 贴纸
    private static final int StickersIndex = 3;

    public GLDefaultFilterGroup() {
        this(initFilters());
    }

    private GLDefaultFilterGroup(List<GLImageFilter> filters) {
        mFilters = filters;
    }

    private static List<GLImageFilter> initFilters() {
        List<GLImageFilter> filters = new ArrayList<GLImageFilter>();
        filters.add(BeautyfyIndex, FilterManager.getFilter(GLFilterType.REALTIMEBEAUTY));
        filters.add(WhiteOrRed,    FilterManager.getFilter(GLFilterType.WHITENORREDDEN));
        return filters;
    }

    @Override
    public void setBeautifyLevel(float percent) {
        ((GLRealtimeBeautyFilter)mFilters.get(BeautyfyIndex)).setSmoothOpacity(percent);
    }

    @Override
    public void changeFilter(GLFilterType type) {
                GLFilterIndex index = FilterManager.getIndex(type);
        if (index == GLFilterIndex.BeautyIndex) {
            changeBeautyFilter(type);
        }
    }


    /**
     * 切换美颜滤镜
     * @param type
     */
    private void changeBeautyFilter(GLFilterType type) {
        if (mFilters != null) {
            mFilters.get(BeautyfyIndex).release();
            mFilters.set(BeautyfyIndex, FilterManager.getFilter(type));
            // 设置宽高
            mFilters.get(BeautyfyIndex).onInputSizeChanged(mImageWidth, mImageHeight);
            mFilters.get(BeautyfyIndex).onDisplayChanged(mDisplayWidth, mDisplayHeight);
        }
    }

    /**
     * 切换颜色滤镜
     * @param type
     */
    private void changeColorFilter(GLFilterType type) {
        if (mFilters != null) {
            mFilters.get(ColorIndex).release();
            mFilters.set(ColorIndex, FilterManager.getFilter(type));
            // 设置宽高
            mFilters.get(ColorIndex).onInputSizeChanged(mImageWidth, mImageHeight);
            mFilters.get(ColorIndex).onDisplayChanged(mDisplayWidth, mDisplayHeight);
        }
    }

    /**
     * 切换瘦脸大眼滤镜
     * @param type
     */
    private void changeFaceStretchFilter(GLFilterType type) {
        if (mFilters != null) {
            mFilters.get(FaceStretchIndex).release();
            mFilters.set(FaceStretchIndex, FilterManager.getFilter(type));
            // 设置宽高
            mFilters.get(FaceStretchIndex).onInputSizeChanged(mImageWidth, mImageHeight);
            mFilters.get(FaceStretchIndex).onDisplayChanged(mDisplayWidth, mDisplayHeight);
        }
    }

    /**
     * 切换贴纸滤镜
     * @param type
     */
    private void changeStickerFilter(GLFilterType type) {
        if (mFilters != null) {
            mFilters.get(StickersIndex).release();
            mFilters.set(StickersIndex, FilterManager.getFilter(type));
            // 设置宽高
            mFilters.get(StickersIndex).onInputSizeChanged(mImageWidth, mImageHeight);
            mFilters.get(StickersIndex).onDisplayChanged(mDisplayWidth, mDisplayHeight);
        }
    }

    /**
     * 切换彩妆滤镜
     * @param type
     */
    private void changeMakeupFilter(GLFilterType type) {
        // Do nothing, 彩妆滤镜放在彩妆滤镜组里面
    }
}
