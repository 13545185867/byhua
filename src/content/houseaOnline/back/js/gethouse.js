 var vm=new Vue({
      el:"#content",
      data:{
          projectInfos:projectInfo,
          cellInfos:cellInfo,
          buildInfos:buildInfo,
          houseInfos:houseInfo,
          curbuild:curbuild,
          curcell:curcell,
          curfloor:0,
          loading:false,
          floorshow:false
     } ,
      methods:{
        toggleban:function(a,b){
        	this.loading=true;
          if(b=='0'){
            this.curbuild=a;
            var _this = this;
			$.ajax({
				url: serverroot+'/houseaOnline/getHouseByCB.html',
				dataType: "text",
				data: {'bid':_this.curbuild,'cid':_this.curcell,'b':b},
				success: function(data){
				 console.log(data);
					var data = JSON.parse(data);
				   if(data.success){
					   _this.houseInfos=data.obj.houseInfos;
					   _this.cellInfos =data.obj.cellInfos;
					   _this.curcell = data.obj.curcell;
					 //  alert(this.tabconlist);
				   } else {
					  alert("出错啦！");
				   }
				   _this.loading=false;
				}
			});
          }
          if(b=='1'){
            this.curcell=a;
            var _this = this;
			$.ajax({
				url: serverroot+'/houseaOnline/getHouseByCB.html',
				dataType: "text",
				data: {'bid':_this.curbuild,'cid':_this.curcell,'b':b},
				success: function(data){
				 console.log(data);
					var data = JSON.parse(data);
				   if(data.success){
					   _this.houseInfos=data.obj;
					 //  alert(this.tabconlist);
				   } else {
					  alert("出错啦！");
				   }
				   _this.loading=false;
				}
			});
          }



        } 
      }
  });