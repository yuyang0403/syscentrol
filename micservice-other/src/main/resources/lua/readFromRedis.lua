local list=redis.call('hgetall','PROD:5');
return cjson.encode(list);