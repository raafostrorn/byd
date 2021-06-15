
var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		showList : true,
		superviseList: [],
		supervise:{}
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
		reload:function(){
			vm.showList=true
		},
		addNew: function(id){
			vm.showList = false;
			vm.title = "新增";
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
		getInfo : function(id) {
			$.get(baseURL + "supervise/info?docid=" + id, function(r) {
				vm.supervise = r.supervise;
				console.log(vm.supervise);
			});
		},
		saveOrUpdate: function (event) {
			$.ajax({
				type: "POST",
			    url: baseURL + "supervise/update",
                contentType: "application/json",
			    data: JSON.stringify(vm.supervise),
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
		item : {},
		index : ''
	},
	template : [ 
	'<div class="row h-2">',
	'<div class="col-md-1 h-5">{{ index+1 }}</div>',
	'<div class="content  col-md-11 h-0">',
		'<div class="row h-3 w-2">',
			'<div class="col-md-10">',
				'<p class="desc">{{item.thread_context}}</p>',
				'<p class="cont">{{item.thread_context}}</p>',
				'<a href="" class="pull-right"><i class="glyphicon glyphicon-triangle-bottom"></i> <span>展开</span></a>',
			'</div>',
			'<div class="col-md-2">',
				'<button type="button" onclick="addNew(this)" :docid="item.id" class="btn btn-primary btn-xs">',
					'<i class="glyphicon glyphicon-plus"></i> 新增',
				'</button>',
			'</div>',
		'</div>',
		'<div class="row h-1 w-1" v-for="(tab,index) in item.result" >',
			'<div class="col-md-2">1</div>',
			'<div class="col-md-1">比亚迪-宋</div>',
			'<div class="col-md-2">性价比-性价比-性价比-价格-配置</div>',
			'<div class="col-md-2">便宜-正面- 2-其它</div>',
			'<div class="col-md-1">对比-1</div>',
			'<div class="col-md-2">用车景</div>',
			'<div class="col-md-2">',
				'<button type="button" class="btn btn-info btn-xs">详</button>&nbsp;',
				'<button type="button" class="btn btn-danger btn-xs">删</button>&nbsp;',
				'<button type="button" class="btn btn-primary btn-xs">增</button>',
			'</div>',
		'</div>',
	'</div>',
	'</div>'].join('')
});

//注册菜单组件
Vue.component('variantItem', variantItem);


function addNew(cur) {
	vm.getInfo($(cur).attr("docid"))
	vm.title="新增";
	vm.showList=false;
}