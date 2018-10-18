local prodValue=ARGV[1];
-- 1.现将整个活动放入缓存中
local key='PROD';
local expire_time=10*60;
local jsonValue=cjson.decode(prodValue);
for k,v in pairs(jsonValue) do
	if k=='items' then
		for i=1,#v,1 do
			--redis.call('hset',key..':ITEM:'..i,k,cjson.encode(v[i]));
			for k1,v1 in pairs(v[i]) do
				if type(v1)~='table' then
					redis.call('hset',key..':'..jsonValue.id..':ITEM:'..v[i].id,k1,v1);
					redis.call('expire',key..':'..jsonValue.id..':ITEM:'..v[i].id,expire_time);
				elseif k1=='product' or k1=='poi' then
					for k2,v2 in pairs(v1) do
						redis.call('hset',key..':'..jsonValue.id..':ITEM:'..v[i].id..':'..k1,k2,v2);
						redis.call('expire',key..':'..jsonValue.id..':ITEM:'..v[i].id..':'..k1,expire_time);
					end
				end
			end
		end
		redis.call('hset',key..':'..jsonValue.id,k,cjson.encode(v));
		redis.call('expire',key..':'..jsonValue.id,expire_time);
	else
		redis.call('hset',key..':'..jsonValue.id,k,v);
		redis.call('expire',key..':'..jsonValue.id,expire_time);
	end	
end
return true;
