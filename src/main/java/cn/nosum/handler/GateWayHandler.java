package cn.nosum.handler;

import cn.nosum.http.Request;
import cn.nosum.http.Response;
import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public  class GateWayHandler extends ChannelInboundHandlerAdapter {
		private String msg ="请求URL是：";
		@Override
		public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
			if (msg instanceof Request){
				Request request = (Request) msg;
				Response response = new Response(ctx,request.getReq());
				// 实际业务处理
				JSONObject obj = new JSONObject();
				obj.put("msg",this.msg+request.getUrl());
				response.write(obj);
			}
		}

		@Override
		public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

		}
	}