package com.noslowq.newsql.newsql.dto;

/*  Created by heyu on 2019-10-06. 
    usage:     
*/

import com.noslowq.newsql.newsql.persistence.ddl.TemplateSqlDO;

public class TemplateSqlDOExt extends TemplateSqlDO {

    private boolean shoudNotify = false;

    public boolean isShoudNotify() {
        return shoudNotify;
    }

    public void setShoudNotify(boolean shoudNotify) {
        this.shoudNotify = shoudNotify;
    }

}
