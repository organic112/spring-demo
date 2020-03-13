package com.potato112.springdemo.jms.doclock;

import com.potato112.springdemo.jms.crud.CRUDServiceBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class DocLockDao extends CRUDServiceBean<DocLock> {


    // TODO implement, but check another impl of DocLockDAO !


}
