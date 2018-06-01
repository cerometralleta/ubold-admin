package com.ubold.admin.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * Created by lenovo on 2017/11/11.
 */
@Data
@Entity
@Table(name = "tb_rbac_resource")
public class Resources extends Auditable {

    @ApiModelProperty(value = "文本")
    private String text;

    @ApiModelProperty(value = "angular路由")
    private String link;

    @ApiModelProperty(value = "外部链接")
    @Column(name = "external_link")
    private String externalLink;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "父级菜单")
    private String parent;

    @ApiModelProperty(value = "类型")
    private Integer type;

    @ApiModelProperty(value = "扩展数据")
    private String extra;

    /** 二级菜单 */
    @Transient
    @ApiModelProperty(value = "二级菜单")
    private List<Resources> children;

//    @Transient
//    @ApiModelProperty(value = "链接target,'_blank' | '_self' | '_parent' | '_top'")
//    private String target;
//
//    @Transient
//    @ApiModelProperty(value = "是否菜单组")
//    private boolean group = true;
//
//    @Transient
//    @ApiModelProperty(value = "徽标数，展示的数字。（注：`group:true` 无效）")
//    private int badge;
//
//    @Transient
//    @JsonProperty(value = "badge_dot")
//    @ApiModelProperty(value = "徽标数，显示小红点")
//    private boolean badgeDot;
//
//    @Transient
//    @JsonProperty(value = "badge_status")
//    @ApiModelProperty(value = "徽标 Badge 颜色 （默认：error， 所有颜色值见：https://github.com/cipchk/ng-alain/blob/master/_documents/utils.md#色彩）")
//    private String badgeStatus;
//
//    @Transient
//    @ApiModelProperty(value = "是否隐藏菜单")
//    private boolean hide;
//
//    @Transient
//    @ApiModelProperty(value = "隐藏面包屑，指 `page-header` 组件的自动生成面包屑时有效")
//    private boolean hideInBreadcrumb;
//
//    @Transient
//    @JsonProperty(value = "shortcut_root")
//    @ApiModelProperty(value = "快捷菜单根节点")
//    private boolean shortcutRoot;
//
//    @Transient
//    @ApiModelProperty(value = "是否快捷菜单项")
//    private boolean shortcut;
//
//    @Transient
//    @ApiModelProperty(value = "i18n主键")
//    private String i18n;
}
