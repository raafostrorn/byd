
var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		sentencePattern: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.sentencePattern = {};
		},
		downexcel:function(){
			location.href = baseURL + "sentencepattern/export?token=" + token;
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
				}
			});
		},
	}
});