#! /bin/sh   
start(){  
 exec java -Dnosum-gateway -jar nosum-gateway-v1-jar-with-dependencies.jar -XX:+UseG1GC >> /dev/null 2>&1 &
 exec ps -ef | grep nosum-gateway | grep -v "grep"
}  
stop(){  
  ps -ef | grep nosum-gateway| grep -v "grep" | cut -c 9-15 | xargs kill -9
}

status(){  
 ps -ef | grep nosum-gateway | grep -v "grep"
}

logs(){  
 tailf logs/nosum-gateway.log
}    
  
case "$1" in  
start)  
start  
;;  
stop)  
stop  
;; 
status)  
status  
;;
logs)  
logs  
;;
*)  
printf 'Usage: %s {start|stop|status|logs}\n' "$prog"
exit 1  
;;  
esac
