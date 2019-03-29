/**
 * .
 */

package com.oseasy.demweb.common.config;

import java.util.List;

import com.oseasy.com.common.config.Sval;
import com.oseasy.com.common.utils.IPath;
import com.oseasy.com.common.utils.PathMsvo;
import com.oseasy.com.common.utils.PathMvo;
import com.oseasy.com.common.utils.SupPath;

/**
 * [DEMO]系统模块路径常量.
 * @author chenhao
 */
public class DemoPath extends SupPath implements IPath{
    public DemoPath() {
        super();
    }

    @Override
    public Sval.Emkey emkey() {
        return Sval.Emkey.DEMO;
    }

    @Override
    public List<PathMsvo> mskeys() {
        return DemoSval.DemoEmskey.toPmsvos();
    }

    @Override
    public IPath path() {
        return DemoSval.path;
    }

    @Override
    public PathMvo curmkey() {
        return mkey(mkey());
    }
}
