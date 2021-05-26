var setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "parentId",
            rootPId: -1
        },
        key: {
            url:"nourl"
        }
    }
};
var ztree;

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		dimension: {
			parentId:0,
		}
	},
	methods: {
        getMenu: function(){
            //加载菜单树
            $.get(baseURL + "dimension/list", function(r){
            	//添加顶级菜单
        		r.push({id:0,name:"主维度",parentId:-1,open:true});
        		
                ztree = $.fn.zTree.init($("#menuTree"), setting, r);
                var node = ztree.getNodeByParam("id", vm.dimension.parentId);
                ztree.selectNode(node);

                vm.dimension.parentName = node.name;
            })
        },
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.dimension = {parentId:0};
            vm.getMenu();
		},
		update: function (event) {
			var id = vm.getId();
			if(id == null){
				return ;
			}
			
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
			if(vm.validator()){
                return ;
            }
			
			var url = vm.dimension.id == null ? "dimension/save" : "dimension/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.dimension),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功.', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var id = vm.getId();
			if(id == null){
				return ;
			}
			
			confirm('确定要删除选中的维度词库？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "dimension/delete",
                    contentType: "application/json",
				    data: JSON.stringify(id),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功.', function(index){
								vm.reload();
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(id){
			$.get(baseURL + "dimension/info/"+id, function(r){
                vm.dimension = r.dimension;
                vm.getMenu();
            });
		},
		getId: function(){
			var selected = $('#menuTable').bootstrapTreeTable('getSelections');
		    if (selected.length == 0) {
		        alert("请选择一条记录");
		        return null;
		    } else {
		        return selected[0].id;
		    }
		},
        menuTree: function(){
            layer.open({
                type: 1,
                offset: '50px',
                skin: 'layui-layer-molv',
                title: "选择菜单",
                area: ['300px', '450px'],
                shade: 0,
                shadeClose: false,
                content: jQuery("#menuLayer"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    var node = ztree.getSelectedNodes();
                    //选择上级菜单
                    vm.dimension.parentId = node[0].id;
                    vm.dimension.parentName = node[0].name;

                    layer.close(index);
                }
            });
        },
        validator: function () {
        	if(isBlank(vm.dimension.parentName)){
        		alert("维度名称不能为空");
        		return true;
        	}
            if(isBlank(vm.dimension.name)){
                alert("上级维度不能为空");
                return true;
            }
        },
		reload: function (event) {
			vm.showList = true;
            Menu.table.refresh();
		}
	}
});

var Menu = {
    id: "menuTable",
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Menu.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
//        {title: 'ID', field: 'id', visible: false, align: 'center', valign: 'middle', width: '80px'},
        {title: '维度名称', field: 'name', align: 'center', valign: 'middle', sortable: true, width: '200px'},
        {title: '属性', field: 'attributes', align: 'center', valign: 'middle', sortable: true, width: '200px'},
        {title: '描述', field: 'description', align: 'center', valign: 'middle', sortable: true, width: '200px'},
        {title: 'json', field: 'json', align: 'center', valign: 'middle', sortable: true}]
    return columns;
};

$(function () {
    var colunms = Menu.initColumn();
    var table = new TreeTable(Menu.id, baseURL + "dimension/list", colunms);
    table.setExpandColumn(0);
    table.setIdField("id");
    table.setCodeField("id");
    table.setParentCodeField("parentId");
    table.setExpandAll(false);
    table.init();
    Menu.table = table;
});