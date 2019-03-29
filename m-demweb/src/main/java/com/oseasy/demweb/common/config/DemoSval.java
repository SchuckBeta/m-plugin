/**
 * .
 */

package com.oseasy.demweb.common.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.oseasy.com.common.config.Sval;
import com.oseasy.com.common.utils.PathMsvo;

/**
 * [DEMO]系统模块常量类.
 * @author chenhao
 */
public class DemoSval extends Sval{
    protected static final Logger logger = LoggerFactory.getLogger(DemoSval.class);
    public static DemoPath path = new DemoPath();

    public enum DemoEmskey {
        DEMO("demo", "样例模块");

        private String key;//url
        private String remark;
        private DemoEmskey(String key, String remark) {
            this.key = key;
            this.remark = remark;
        }

        public static List<PathMsvo> toPmsvos() {
            List<PathMsvo> entitys = Lists.newArrayList();
            for (DemoEmskey entity : DemoEmskey.values()) {
                entitys.add(new PathMsvo(entity.k(), entity.getRemark()));
            }
            return entitys;
        }
        public String k() {
            return key;
        }

        public String getRemark() {
            return remark;
        }
    }
}
