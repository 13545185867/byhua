Vue.component("tabcon1", {
	template: "#tabcon1",

	methods: {
		 
	}
})
Vue.component("tabcon2", {
	template: "#tabcon2"
})
Vue.component("tabcon3", {
	props: ['styles','viewstyle','configinfo'],
	template: "#tabcon3",
	data: {  
    		
    }, 
	methods: {
		setStyle:function(style){
			console.log(style);
			vm.$emit('listenToChildEventtab3',style);
		},
		configinfoblur:function(){
			console.log(this.configinfo);
			vm.$emit('listenToChildEventtab33',this.configinfo);
	    }
	}
})
Vue.component("tabcon4", {
	template: "#tabcon4"
})


var vm = new Vue({
    el: '#dailogForm',
    data: {
        tabs: ["基本信息", "参与设置","显示设置"],
        tabContents: ["tabcon1", "tabcon2","tabcon3","tabcon4"],
        num: 0,
        baseinfo:baseinfo,
        viewstyle:viewstyle, 
        configinfo:configinfo     ,
		total:3 ,
		styles:[{"file":"wapstyle.css","color":"gray","name":"默认风格"},{"file":"wapstyle_chinared.css","color":"#e60000","name":"中国红"},{"file":"wapstyle_black.css","color":"#000","name":"经典黑"},{"file":"wapstyle_deepblue.css","color":"#2c3e50","name":"优雅蓝"},{"file":"wapstyle_green.css","color":"#2ecc71","name":"绿色心情"},{"file":"wapstyle_orange.css","color":"#ff9900","name":"香橙情缘"},{"file":"wapstyle_pink.css","color":"#ff00cc","name":"粉色青春"},{"file":"wapstyle_red.css","color":"#e74c3c","name":"红色活力"},{"file":"wapstyle_yellow.css","color":"#f1c40f","name":"金色阳光"},{"file":"wapstyle_hulan.css","color":"#48b9bd","name":"深湖蓝"},{"file":"wapstyle_n1.css","color":"#7f5555","name":"浅棕"},{"file":"wapstyle_n2.css","color":"#29abdd","name":"清新蓝"}]
    }, 
    computed: {
    	newactname: function () {
    	  const vmdemo = vm.$refs.demoframe.contentWindow.vm;
			vmdemo.baseinfo=this.baseinfo;
			console.log(vm.$data.baseinfo);
			return this.baseinfo.actName;
      }
    },
    methods: {
        tab:function(index) {
            this.num = index;
        },
		last:function(){
			if(this.num>0){
				this.num = this.num-1; 
			}
		},
		next:function(){
			if(this.num<this.total-1){
				var form = $("#"+this.tabContents[this.num]).Validform();
				var flag = form.check();
				if(flag){
					this.num = this.num+1; 
				}else{
					alert("表单验证失败！");
				}

			}
		},
		cancel:function(){
			if(confirm("真的要取消吗？")){
				history.back(-1);
			}
		},
		submit:function(){
			if(this.num==this.total-1){
				var flag= false;
				for(var i=0;i<this.total;i++){
					var form = $("#"+this.tabContents[i]).Validform();
					flag = form.check();
					if(!flag){
						this.num =i; 
					}
				}
				if(flag){
					if(confirm("确认提交吗")){
						ajaxdoFormSubmit('dailogForm');
            		}
				}else{
					alert("表单验证失败！");
				}

			}
		}
    }
});



vm.$on('listenToChildEventtab3', function (data) {
	console.log(data);
	vm.$data.viewstyle=data;
	 const vmdemo = this.$refs.demoframe.contentWindow.vm;
	 vmdemo.$data.viewstyle=data;
})
vm.$on('listenToChildEventtab33', function (data) {
	console.log(data);
	vm.$data.configinfo=data;
	 const vmdemo = this.$refs.demoframe.contentWindow.vm;
	 vmdemo.$data.configinfo=data;
})


