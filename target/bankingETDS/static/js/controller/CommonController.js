"use strict";
App.controller("CommonController",["$http","restUrl","$scope","$state","$stateParams","CommonService","$q","$window",function(i,h,k,a,j,g,f,b){var l=this;
l.tan;
l.clientId;
l.branchId;
l.loader="";
l.entity={};
l.search={};
l.entityList=[];
l.dropdown=[];
l.ajax=[];
l.temp={};
l.temp3={};
l.temp1=[];
l.temp2={};
l.company={};
l.currentPage=1;
l.batchColor={};
l.goodsColor=[];
l.b=[];
l.fileName=new FormData();
l.reload=function(){a.reload()
};
l.setTemp1=function(m){l.temp1=m
};
l.logout=function(){a.go("logout")
};
l.changeAction=function(){if(a.current.name=="main"){b.location.reload()
}else{a.go("main")
}};
l.presentDate=function(){return new Date()
};
l.presentMonth=function(){return new Date().getMonth()
};
l.gotoRestPqassword=function(){a.go("resetPass")
};
l.gotoHomePageSC=function(m,n){l.company=name;
l.clientId=m;
a.go("home.homepage",{clientId:m,action:n})
};
l.gotoHomeSCfromHomeWOT=function(m){a.go("home.homepage",{clientId:j.clientId,action:m})
};
l.gotoHomePageWOT=function(n,m){l.company=name;
l.clientId=j.clientId;
a.go("home.homepage",{branchId:n,action:m})
};
l.gotoDetailPage=function(m,n,o){l.company=name;
a.go("home.detail",{entity:m,detailId:n,page:o})
};
l.gotoDetail=function(n,o){l.company=name;
var m=f.defer();
return g.detail(j.clientId,n,o).then(function(p){console.log("Common Controller get Detail..");
return p
})
};
l.download=function(m){d(1000);
l.loader=m;
window.open(l.loader,"_blank")
};
l.deleteFunction=function(q,p,n,r,o,m){if(q=true){g.deleteEntity(j.clientId,p,n.id).then(function(s){console.log(n+" deleted successfully");
r.splice(o,1);
angular.element("#"+m).trigger("click");
$("#successMsg").find(".modal-header").find(".headingMsg").append("Successfull");
$("#successMsg").find(".modal-body").find(".msg").append(" Deleted Successfully");
$("#successMsg").modal()
},function(s){console.error("Error while deleting , "+status);
if(s.exceptionMsg!=null&&s.exceptionMsg!=undefined){$("#errorMsg1").find(".modal-body").find(".msg").append("Can not Delete "+s.entityName+" : "+s.exceptionMsg);
$("#errorMsg1").modal()
}else{for(var t=0;
t<s.fieldErrors.length;
t++){var u=s.fieldErrors[t];
document.getElementById(u.fieldName).innerHTML=u.message
}}})
}};
l.gotoListPage=function(m,n){l.company=name;
l.show=false;
l.entity={};
l.search={};
l.entityList=[];
l.dropdown=[];
l.ajax=[];
l.temp={};
l.temp3={};
l.temp1=[];
l.company={};
l.currentPage=1;
l.batchColor={};
l.goodsColor=[];
l.b=[];
l.fileName=new FormData();
a.go("home.list",{entity:m,page:n})
};
l.searchEntities=function(n,m,o){if(n=true){$.each(l.search,function(p,q){if(q===""||q===null){delete l.search[p]
}});
a.go("home.search",{entity:m,page:o,searchParams:JSON.stringify(l.search)})
}};
l.gotoSearchDetailPage=function(m,n,o){a.go("home.search.detail2",{entity2:m,detailId:n,page2:o})
};
l.gotoList2Page=function(m,n){a.go("home.listBranch",{entity:m,page:n})
};
l.gotoList3Page=function(m,n){a.go("home.listFy",{entity:m,page:n})
};
l.download=function(m){d(1000);
l.loader=m;
window.open(l.loader,"_blank")
};
l.gotoDirectList2Page=function(n,o,m,q,p){if(true){$.each(p,function(r,s){if(s===""||s===null){delete p[r]
}})
}a.go("home.list.list2",{entity:n,page:o,entity2:m,page2:q,searchParams:JSON.stringify(p)})
};
l.activetab2=function(m){if(m==j.entity2){return true
}return false
};
l.getEntityListData=function(){console.log("Common Controller get  getEntityList");
return g.getEntityList()
};
l.submitFile=function(p,n,o,m){if(p==true){console.log("Common Controller submit "+n);
l.entity.clientId=j.clientId;
e();
g.saveDoc(o,n,j.clientId).then(function(q){e();
console.log(n+" added successfully");
angular.element("#"+m).trigger("click");
$("#successMsg").find(".modal-header").find(".headingMsg").append("Successfull");
$("#successMsg").find(".modal-body").find(".msg").append(" Saved Successfully");
$("#successMsg").modal()
},function(q){e();
console.error("Error while creating saving Details, "+status);
if(q.exceptionMsg!=null&&q.exceptionMsg!=undefined){$("#errorMsg").find(".modal-body").find(".msg").append("Can not Save "+q.entityName+" : "+q.exceptionMsg);
$("#errorMsg").modal()
}else{for(var r=0;
r<q.fieldErrors.length;
r++){var s=q.fieldErrors[r];
document.getElementById(s.fieldName).innerHTML=s.message
}}})
}};
var e=function(){angular.element("#viewProgressBar").trigger("click")
};
l.updateFile=function(p,n,o,m){if(p==true){console.log("Common Controller submit "+n);
l.entity.clientId=j.clientId;
if(o.dec.status==undefined||o.dec.status=="Pending Verification"){e();
g.updateDoc(o,n,j.clientId).then(function(q){console.log(n+" updated successfully");
e();
angular.element("#"+m).trigger("click");
$("#successMsg").find(".modal-header").find(".headingMsg").append("Successfull");
$("#successMsg").find(".modal-body").find(".msg").append(" Updated Successfully");
$("#successMsg").modal()
},function(q){e();
console.error("Error while updating Details, "+status);
if(q.exceptionMsg!=null&&q.exceptionMsg!=undefined){$("#errorMsg").find(".modal-body").find(".msg").append("Can not Update "+q.entityName+" : "+q.exceptionMsg);
$("#errorMsg").modal()
}else{for(var r=0;
r<q.fieldErrors.length;
r++){var s=q.fieldErrors[r];
document.getElementById(s.fieldName+"U").innerHTML=s.message
}}})
}else{angular.element("#"+m).trigger("click");
$("#successMsg1").find(".modal-header").find(".headingMsg").append("Information");
$("#successMsg1").find(".modal-body").find(".msg").append(" Update allowed only for Pending Verification");
$("#successMsg1").modal()
}}};
l.gotoList=function(m){l.search={};
l.currentPage=1;
a.go("home.list",{entity:m})
};
l.submit=function(o,n,m){if(o==true){console.log("Common Controller submit "+n);
l.entity.clientId=j.clientId;
g.save(l.entity,n,j.clientId).then(function(p){console.log(n+" added successfully");
l.gotoList(n);
l.gotoListPage(n,n);
angular.element("#"+m).trigger("click");
$("#successMsg").find(".modal-header").find(".headingMsg").append("Successfull!");
$("#successMsg").find(".modal-body").find(".msg").append(" Saved Successfully!");
$("#successMsg").modal()
},function(p){console.error("Error while creating saving Details, "+status);
if(p.exceptionMsg!=null&&p.exceptionMsg!=undefined){$("#errorMsg").find(".modal-body").find(".msg").append("Can not Save "+p.entityName+" : "+p.exceptionMsg);
$("#errorMsg").modal()
}else{for(var q=0;
q<p.fieldErrors.length;
q++){var r=p.fieldErrors[q];
document.getElementById(r.fieldName).innerHTML=r.message
}}})
}};
l.getEntityData=function(){console.log("Common Controller get Entity data");
return g.getEntity()
};
l.ajax1=function(n,o,p){var q={};
q.name=o;
q.term=p;
var m=f.defer();
return g.ajax(n,q,j.clientId).then(function(s){console.log(o+" dynamic drop down");
var r=s;
l.ajax=s;
return s
})
};
l.getEmployeeId=function(m,n){console.log("Common Controller is working");
var o={};
o.employeeCode=m;
if(m!=null){g.search(j.clientId,n,o).then(function(q){console.log(name+" dynamic drop down");
var p=q;
l.search.employeeId=q.id
})
}};
l.getGroupId=function(n,m){console.log("Common Controller is working");
var o={};
o.groupCode=n;
if(n!=null){g.search(j.clientId,m,o).then(function(q){console.log(name+" dynamic drop down");
var p=q;
l.entity.groupId=q.id
})
}};
l.approveUpdate=function(n,m,o){if(n==true){console.log("Common Controller approveData "+m);
l.entity.clientId=j.clientId;
g.approveUpdate(o,m,j.clientId).then(function(p){console.log(m+" updated successfully");
$("#successMsg").find(".modal-header").find(".headingMsg").append("Successfull");
$("#successMsg").find(".modal-body").find(".msg").append(" Updated Successfully");
$("#successMsg").modal()
},function(p){console.error("Error while updating Details, "+status);
if(p.exceptionMsg!=null&&p.exceptionMsg!=undefined){$("#errorMsg").find(".modal-body").find(".msg").append("Can not Update "+p.entityName+" : "+p.exceptionMsg);
$("#errorMsg").modal()
}else{for(var q=0;
q<p.fieldErrors.length;
q++){var r=p.fieldErrors[q];
document.getElementById(r.fieldName).innerHTML=r.message
}}})
}};
l.rejectUpdate=function(n,m,o){if(n==true){console.log("Common Controller rejectData "+m);
l.entity.clientId=j.clientId;
g.rejectUpdate(o,m,j.clientId).then(function(p){console.log(m+" updated successfully");
$("#successMsg").find(".modal-header").find(".headingMsg").append("Successfull");
$("#successMsg").find(".modal-body").find(".msg").append(" Updated Successfully");
$("#successMsg").modal()
},function(p){console.error("Error while updating Details, "+status);
if(p.exceptionMsg!=null&&p.exceptionMsg!=undefined){$("#errorMsg").find(".modal-body").find(".msg").append("Can not Update "+p.entityName+" : "+p.exceptionMsg);
$("#errorMsg").modal()
}else{for(var q=0;
q<p.fieldErrors.length;
q++){var r=p.fieldErrors[q];
document.getElementById(r.fieldName).innerHTML=r.message
}}})
}};
l.updateData=function(o,n,p,m){if(o==true){console.log("Common Controller updateData "+n);
l.entity.clientId=j.clientId;
g.update(p,n,j.clientId).then(function(q){console.log(n+" updated successfully");
angular.element("#"+m).trigger("click");
$("#successMsg").find(".modal-header").find(".headingMsg").append("Successfull");
$("#successMsg").find(".modal-body").find(".msg").append(" Updated Successfully");
$("#successMsg").modal()
},function(q){console.error("Error while updating Details, "+status);
if(q.exceptionMsg!=null&&q.exceptionMsg!=undefined){$("#errorMsg").find(".modal-body").find(".msg").append("Can not Update "+q.entityName+" : "+q.exceptionMsg);
$("#errorMsg").modal()
}else{for(var r=0;
r<q.fieldErrors.length;
r++){var s=q.fieldErrors[r];
document.getElementById(s.fieldName).innerHTML=s.message
}}})
}};
l.updateStatus=function(o,n,p,m){if(o==true){console.log("Common Controller updateData "+n);
l.entity.clientId=j.clientId;
g.updateStatus(p,n,j.clientId).then(function(q){console.log(n+" updated successfully");
angular.element("#"+m).trigger("click");
$("#successMsg").find(".modal-header").find(".headingMsg").append("Successfull");
$("#successMsg").find(".modal-body").find(".msg").append(" Updated Successfully");
$("#successMsg").modal()
},function(q){console.error("Error while updating Details, "+status);
if(q.exceptionMsg!=null&&q.exceptionMsg!=undefined){$("#errorMsg").find(".modal-body").find(".msg").append("Can not Update "+q.entityName+" : "+q.exceptionMsg);
$("#errorMsg").modal()
}else{for(var r=0;
r<q.fieldErrors.length;
r++){var s=q.fieldErrors[r];
document.getElementById(s.fieldName).innerHTML=s.message
}}})
}};
l.date=function(m){m=new Date(m.getTime()+Math.abs(m.getTimezoneOffset()*60000));
return m
};
l.getPage=function(o,n,m){if(o=true){l.currentPage=m;
g.fetch(n,j.clientId,m-1)
}};
l.getNoPage=function(o,n,m){return Math.ceil(g.getCount()/100)
};
l.setIndex=function(m){l.dlIndex=m
};
l.viewProfile=function(m,n){l.loader="static/img/Spinner-1s-200px.gif";
l.loader="api"+m+"/getFile/"+j.clientId+"/"+n+"/"+Date.now()
};
l.viewFile=function(n,m,o){l.temp1=m;
o=o+"Click";
if(m.fileId!=null&&m.fileId!=undefined){c(o);
d(1000);
l.loader="api"+n+"/getFile/"+j.clientId+"/"+m.fileId;
window.open(l.loader,"_blank")
}else{c("no"+o)
}};
l.isFileNull=function(p,n,o,m){if(o.file==null||o.file==undefined){document.getElementById("file-upload").innerHTML="file is required "
}else{l.submitFile(p,n,o,m)
}};
var c=function(m){angular.element("#"+m).trigger("click")
};
var d=function(n){var o=new Date().getTime();
var m=o;
while(m<o+n){m=new Date().getTime()
}}
}]);
App.directive("fileModel",["$parse",function(a){return{restrict:"A",link:function(f,e,d){var c=a(d.fileModel);
var b=c.assign;
e.bind("change",function(){f.$apply(function(){b(f,e[0].files[0])
})
})
}}
}]);