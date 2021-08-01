package top.lemcoo.exam.domain.vo;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import top.lemcoo.exam.common.Constants;

/**
 * 路由显示信息
 *
 * @author zhaowx
 * @Description
 * @date 2021/7/31
 */
@Data
public class MetaVO {

    /**
     * 设置该路由在侧边栏和面包屑中展示的名字
     */
    private String title;

    /**
     * 设置该路由的图标，对应路径src/assets/icons/svg
     */
    private String icon;

    /**
     * 设置为true，则不会被 <keep-alive>缓存
     */
    private Boolean noCache;

    /**
     * 内链地址（http(s)://开头）
     */
    private String link;

    public MetaVO()
    {
    }

    public MetaVO(String title, String icon)
    {
        this.title = title;
        this.icon = icon;
    }

    public MetaVO(String title, String icon, boolean noCache)
    {
        this.title = title;
        this.icon = icon;
        this.noCache = noCache;
    }

    public MetaVO(String title, String icon, String link)
    {
        this.title = title;
        this.icon = icon;
        this.link = link;
    }

    public MetaVO(String title, String icon, boolean noCache, String link)
    {
        this.title = title;
        this.icon = icon;
        this.noCache = noCache;
        if (StringUtils.startsWithAny(link, Constants.HTTP, Constants.HTTPS))
        {
            this.link = link;
        }
    }

}
