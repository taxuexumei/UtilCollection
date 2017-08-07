package com.zhiyou100.crm.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.zhiyou100.crm.dao.TransferMoneyDao;
import com.zhiyou100.crm.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService{

	@Autowired
	private TransferMoneyDao transferDao;
	/* java 异常分类
	 * http://blog.csdn.net/woshixuye/article/details/8230407
	 * 只有java语言提供了Checked异常，Java认为Checked异常都是可以被处理的异常，所以Java程序必须显示处理Checked异常。如果程序没有处理Checked异常，该程序在编译时就会发生错误无法编译。这体现了Java的设计哲学：没有完善错误处理的代码根本没有机会被执行。对Checked异常处理方法有两种
       1 当前方法知道如何处理该异常，则用try...catch块来处理该异常。
       2 当前方法不知道如何处理，则在定义该方法是声明抛出该异常
	 * 我们比较熟悉的Checked异常有  事物不會回滾
       Java.lang.ClassNotFoundException
       Java.lang.NoSuchMetodException
       java.io.IOException
       
       ②RuntimeException 事物會回滾
        Runtime如除数是0和数组下标越界等，其产生频繁，处理麻烦，若显示申明或者捕获将会对程序的可读性和运行效率影响很大。所以由系统自动检测并将它们交给缺省的异常处理程序。当然如果你有处理要求也可以显示捕获它们
	 *  Java.lang.ArithmeticException
        Java.lang.ArrayStoreExcetpion
        Java.lang.ClassCastException
        Java.lang.IndexOutOfBoundsException
J       ava.lang.NullPointerException
	 * 
	 * 三、Error 事物會回滾
                    当程序发生不可控的错误时，通常做法是通知用户并中止程序的执行。与异常不同的是Error及其子类的对象不应被抛出。
        Error是throwable的子类，代表编译时间和系统错误，用于指示合理的应用程序不应该试图捕获的严重问题。
        Error由Java虚拟机生成并抛出，包括动态链接失败，虚拟机错误等。程序对其不做处理
        
                  如果異常被try catch 那麼無論什麼樣的異常，spring事物都不會回滾
                  
        但是實際項目中又幾乎都需要這樣做 怎麼辦？ 兩種解決方案
        1 例如service层处理事务，那么service中的方法中不做异常捕获，或者在catch语句中最后增加throw new RuntimeException()语句，
        以便让aop捕获异常再去回滚，并且在service上层（webservice客户端，view层action）要继续捕获这个异常并处理
        2 在service层方法的catch语句中增加：TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();语句，手动回滚
        ，这样上层就无需去处理异常（實際項目常用做法）
        
        @Transactional可作用与接口 接口方法 类和类方法上，当作用于类上的时候，该类的所有public方法该类型的事物属性，
        同时我们也可以在方法级别使用该注解覆盖类级别的定义
        虽然@Transactional可作用与接口 接口方法 类和类方法上，但spring建议不要在接口以及接口方法上使用该注解，因为这样只有基于接口的
        代理时他才会生效，另外 @Transactional只应该作用于pulic 类型的方法上，这是有spring aop 的决定的，如果在protectes private或者默认可见性
        上使用该注解，这将被忽略也不会抛出任何异常
        默认情况下值有来自外部的方法调用才会被aop 代理捕获，也就是类内部调用本类内部的其他方法不会引起事物行为，即使被@Transactional注解修饰
     rollbackfor   throwable
     noRollbackFor throwable
           主容器和web 容器扫描重复也可能造成事物方面的问题
     
     
	 * 
	 * 声明式事物 注解
	 */

	@Override
	@Transactional(isolation=Isolation.DEFAULT,readOnly=false,propagation=Propagation.REQUIRED)
	public void transferMoney(Integer userAId, Integer userBId,Integer money) {

		try {
			transferDao.transferMoney1(userAId, money);
			int i =1/0;
			transferDao.transferMoney2(userBId, money);
			
		} catch (Exception e) {
			//throw new RuntimeException();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		
		
	}

}
