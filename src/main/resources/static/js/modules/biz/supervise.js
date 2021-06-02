
var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		superviseList: []
	},
	created : function() {
		this.queryPage();
	},
	mounted : function() {
	},
	watch : {
		
	},
	methods: {
		queryPage:function(){
			$.ajax({
				type: "POST",
			    url: baseURL + "supervise/list",
                contentType: "application/json",
			    //data: JSON.stringify(vm.sentencePattern),
			    success: function(r){
			    	console.log(r.page);
			    	vm.superviseList = r.page;
				}
			});
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.sentencePattern = {};
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
			var url = vm.sentencePattern.id == null ? "sentencepattern/save" : "sentencepattern/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.sentencePattern),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				},
				error : function(error) {
					// error错误描述信息
					alert("error")
					return false;
				}
			});
		},
	}
});


var variantItem = Vue.extend({
	name : 'variant-item',
	props : {
		item : {}
	},
	template : [ 
'<div class="row h-2">',
'<div class="col-md-1 h-5">001</div>',
'<div class="content  col-md-11 h-0">',
	'<div class="row h-3 w-2">',
		'<div class="col-md-10">',
			'<p class="desc">宋价格比唐便宜十来万块钱，宋的乘坐空间大，没有噪音宋价格比唐便宜十来万块钱，宋的乘坐空间大，没有噪音宋价格比唐便宜十来万块钱，宋的乘坐空间大，没有噪音宋价格比唐便宜十来万块钱，宋的乘坐空间大，没有噪音宋价格比唐便宜十来万块钱，宋的乘坐空间大，没有噪音宋价格</p>',
			'<p class="cont">宋价格比唐便宜十来万块钱，宋的乘坐空间大，没有噪音宋价格比唐便宜十来万块钱，宋的乘坐空间大，没有噪音宋价格比唐便宜十来万块钱，宋的乘坐空间大，没有噪音宋价格比唐便宜十来万块钱，宋的乘坐空间大，没有噪音宋价格比唐便宜十来万块钱，宋的乘坐空间大，没有噪音宋价格比唐便宜十来万块钱，宋的乘坐空间大，没有噪音宋价格比唐便宜十来万块钱，宋的乘坐空间大，没有噪音宋价格比唐便宜十来万块钱，宋的乘坐空间大，没有噪音宋价格比唐便宜十来万块钱，宋的乘坐空间大，没有噪音宋价格比唐便宜十来万块钱，宋的乘坐空间大，没有噪音宋价格比唐便宜十来万块钱，宋的乘坐空间大，没有噪音宋价格比唐便宜十来万块钱，宋的乘坐空间大，没有噪音宋价格比唐便宜十来万块钱，宋的乘坐空间大，没有噪音宋价格比唐便宜十来万块钱，宋的乘坐空间大，没有噪音宋价格比唐便宜十来万块钱，宋的乘坐空间大，没有噪音宋价格比唐便宜十来万块钱，宋的乘坐空间大，没有噪音宋价格比唐便宜十来万块钱，宋的乘坐空间大，没有噪音宋价格比唐便宜十来万块钱，宋的乘坐空间大，没有噪音宋价格比唐便宜十来万块钱，宋的乘坐空间大，没有噪音宋价格比唐便宜十来万块钱，宋的乘坐空间大，没有噪音...</p>',
			'<a href="" class="pull-right"><i class="glyphicon glyphicon-triangle-bottom"></i> <span>展开</span></a>',
		'</div>',
		'<div class="col-md-2">',
			'<button type="button" class="btn btn-primary btn-xs">',
				'<i class="glyphicon glyphicon-plus"></i> 增',
			'</button>',
		'</div>',
	'</div>',
	'<div class="row h-1 w-1">',
		'<div class="col-md-2">1）宋价格比唐便宜十来万块钱</div>',
		'<div class="col-md-1">比亚迪-宋</div>',
		'<div class="col-md-2">性价比-性价比-性价比-价格-配置</div>',
		'<div class="col-md-2">便宜-正面- 2-其它</div>',
		'<div class="col-md-1">对比-1</div>',
		'<div class="col-md-2">用车场景</div>',
		'<div class="col-md-2">',
			'<button type="button" class="btn btn-info btn-xs">详</button>',
			'<button type="button" class="btn btn-danger btn-xs">删</button>',
			'<button type="button" class="btn btn-primary btn-xs">改</button>',
		'</div>',
	'</div>',
	'<div class="row h-1 w-1">',
		'<div class="col-md-2">1）宋价格比唐便宜十来万块钱</div>',
		'<div class="col-md-1">比亚迪-宋</div>',
		'<div class="col-md-2">性价比-性价比-性价比-价格-配置</div>',
		'<div class="col-md-2">便宜-正面- 2-其它</div>',
		'<div class="col-md-1">对比-1</div>',
		'<div class="col-md-1">用车场景</div>',
		'<div class="col-md-3">',
			'<button type="button" class="btn btn-info btn-xs">详</button>',
			'<button type="button" class="btn btn-danger btn-xs">删</button>',
			'<button type="button" class="btn btn-primary btn-xs">改</button>',
		'</div>',
	'</div>',
	'<div class="row h-1 w-1">',
		'<div class="col-md-2">1）宋价格比唐便宜十来万块钱</div>',
		'<div class="col-md-1">比亚迪-宋</div>',
		'<div class="col-md-2">性价比-性价比-性价比-价格-配置</div>',
		'<div class="col-md-2">便宜-正面- 2-其它</div>',
		'<div class="col-md-1">对比-1</div>',
		'<div class="col-md-1">用车场景</div>',
		'<div class="col-md-3">',
			'<button type="button" class="btn btn-info btn-xs">详</button>',
			'<button type="button" class="btn btn-danger btn-xs">删</button>',
			'<button type="button" class="btn btn-primary btn-xs">改</button>',
		'</div>',
	'</div>',
	'<div class="row h-1 w-1">',
		'<div class="col-md-2">1）宋价格比唐便宜十来万块钱</div>',
		'<div class="col-md-1">比亚迪-宋</div>',
		'<div class="col-md-2">性价比-性价比-性价比-价格-配置</div>',
		'<div class="col-md-2">便宜-正面- 2-其它</div>',
		'<div class="col-md-1">对比-1</div>',
		'<div class="col-md-1">用车场景</div>',
		'<div class="col-md-3">',
			'<button type="button" class="btn btn-info btn-xs">详</button>',
			'<button type="button" class="btn btn-danger btn-xs">删</button>',
			'<button type="button" class="btn btn-primary btn-xs">改</button>',
		'</div>',
	'</div>',
'</div>',
'</div>'].join('')
});

//注册菜单组件
Vue.component('variantItem', variantItem);