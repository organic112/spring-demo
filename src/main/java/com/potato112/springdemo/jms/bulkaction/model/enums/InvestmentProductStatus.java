package com.potato112.springdemo.jms.bulkaction.model.enums;

import com.potato112.springdemo.jms.bulkaction.model.interfaces.SysStatus;

import java.util.Set;

public enum InvestmentProductStatus implements SysStatus {


    PROCESSED {
        @Override
        public Set<SysStatus> getExitStatuses() {
            return null;
        }
    },
    NOT_PROCESSED {
        @Override
        public Set<SysStatus> getExitStatuses() {
            return null;
        }
    },

}
