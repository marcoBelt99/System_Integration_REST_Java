update ordersdb.customerorder set exportTimestamp=null where orderId IN (1,2,3); 
delete from salesdb.sale where saleId<100;
commit;