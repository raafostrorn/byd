$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'emotion/list',
        datatype: "json",
        colModel: [			
			/*{ label: 'id', name: 'id', index: 'id', width: 50, key: true },*/
			{ label: '情感', name: 'emotionWord', index: 'emotion_word', width: 80 }, 			
			/*{ label: '维度id', name: 'dimensionId', index: 'dimension_id', width: 80 }, 	*/		
			{ label: '维度', name: 'dimensionName', index: 'dimension_name', width: 80 }, 			
			{ label: '极性值', name: 'worth', index: 'worth', width: 80 }, 			
			{ label: '故障', name: 'isfault', index: 'isfault', width: 80 }, 			
			{ label: '别名', name: 'emotionNickname', index: 'emotion_nickname', width: 80 }			
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		emotion: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.emotion = {};
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
		downexcel:function(){
			location.href = baseURL + "emotion/export?token=" + token;
		},
		saveOrUpdate: function (event) {
			var url = vm.emotion.id == null ? "emotion/save" : "emotion/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.emotion),
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
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "emotion/delete",
                    contentType: "application/json",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(id){
			$.get(baseURL + "emotion/info/"+id, function(r){
                vm.emotion = r.emotion;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});