if(typeof module!=="undefined"&&typeof exports!=="undefined"&&module.exports===exports){module.exports="ui.router"
}(function(Q,G,r){var E=G.isDefined,w=G.isFunction,o=G.isString,O=G.isObject,F=G.isArray,c=G.forEach,q=G.extend,V=G.copy,h=G.toJson;
function v(aa,Z){return q(new (q(function(){},{prototype:aa}))(),Z)
}function P(Z){c(arguments,function(aa){if(aa!==Z){c(aa,function(ac,ab){if(!Z.hasOwnProperty(ab)){Z[ab]=ac
}})
}});
return Z
}function k(ab,Z){var aa=[];
for(var ac in ab.path){if(ab.path[ac]!==Z.path[ac]){break
}aa.push(ab.path[ac])
}return aa
}function f(aa){if(Object.keys){return Object.keys(aa)
}var Z=[];
c(aa,function(ac,ab){Z.push(ab)
});
return Z
}function z(ac,aa){if(Array.prototype.indexOf){return ac.indexOf(aa,Number(arguments[2])||0)
}var Z=ac.length>>>0,ab=Number(arguments[2])||0;
ab=(ab<0)?Math.ceil(ab):Math.floor(ab);
if(ab<0){ab+=Z
}for(;
ab<Z;
ab++){if(ab in ac&&ac[ab]===aa){return ab
}}return -1
}function R(ag,ac,Z,af){var ah=k(Z,af),ai,ad={},ae=[];
for(var ab in ah){if(!ah[ab]||!ah[ab].params){continue
}ai=f(ah[ab].params);
if(!ai.length){continue
}for(var aa in ai){if(z(ae,ai[aa])>=0){continue
}ae.push(ai[aa]);
ad[ai[aa]]=ag[ai[aa]]
}}return q({},ad,ac)
}function J(aa,Z,ad){if(!ad){ad=[];
for(var ae in aa){ad.push(ae)
}}for(var ac=0;
ac<ad.length;
ac++){var ab=ad[ac];
if(aa[ab]!=Z[ab]){return false
}}return true
}function j(ab,Z){var aa={};
c(ab,function(ac){aa[ac]=Z[ac]
});
return aa
}function B(ab,aa){var Z={};
c(ab,function(ac){Z[ac[aa]]=ac
});
return Z
}function A(aa){var ab={};
var Z=Array.prototype.concat.apply(Array.prototype,Array.prototype.slice.call(arguments,1));
c(Z,function(ac){if(ac in aa){ab[ac]=aa[ac]
}});
return ab
}function D(ab){var ac={};
var aa=Array.prototype.concat.apply(Array.prototype,Array.prototype.slice.call(arguments,1));
for(var Z in ab){if(z(aa,Z)==-1){ac[Z]=ab[Z]
}}return ac
}function N(ab,aa){var Z=F(ab)?[]:{};
c(ab,function(ad,ac){Z[ac]=w(aa)?aa(ad):ad[aa]
});
return Z
}function M(aa,ac){var ab=F(aa);
var Z=ab?[]:{};
c(aa,function(ae,ad){if(ac(ae,ad)){Z[ab?Z.length:ad]=ae
}});
return Z
}function K(aa,ab){var Z=F(aa)?[]:{};
c(aa,function(ad,ac){Z[ac]=ab(ad,ac)
});
return Z
}G.module("ui.router.util",["ng"]);
G.module("ui.router.router",["ui.router.util"]);
G.module("ui.router.state",["ui.router.router","ui.router.util"]);
G.module("ui.router",["ui.router.state"]);
G.module("ui.router.compat",["ui.router"]);
i.$inject=["$q","$injector"];
function i(ab,af){var aa=1,ag=2,ac={},ae=[],ad=ac,Z=q(ab.when(ac),{$$promises:ac,$$values:ac});
this.study=function(aj){if(!O(aj)){throw new Error("'invocables' must be an object")
}var an=f(aj||{});
var am=[],al=[],ai={};
function ak(ap,ao){if(ai[ao]===ag){return
}al.push(ao);
if(ai[ao]===aa){al.splice(0,z(al,ao));
throw new Error("Cyclic dependency: "+al.join(" -> "))
}ai[ao]=aa;
if(o(ap)){am.push(ao,[function(){return af.get(ap)
}],ae)
}else{var aq=af.annotate(ap);
c(aq,function(ar){if(ar!==ao&&aj.hasOwnProperty(ar)){ak(aj[ar],ar)
}});
am.push(ao,ap,aq)
}al.pop();
ai[ao]=ag
}c(aj,ak);
aj=al=ai=null;
function ah(ao){return O(ao)&&ao.then&&ao.$$promises
}return function(ao,ay,aB){if(ah(ao)&&aB===r){aB=ay;
ay=ao;
ao=null
}if(!ao){ao=ad
}else{if(!O(ao)){throw new Error("'locals' must be an object")
}}if(!ay){ay=Z
}else{if(!ah(ay)){throw new Error("'parent' must be a promise returned by $resolve.resolve()")
}}var aq=ab.defer(),aC=aq.promise,aw=aC.$$promises={},az=q({},ao),av=1+am.length/3,ax=false;
function at(){if(!--av){if(!ax){P(az,ay.$$values)
}aC.$$values=az;
aC.$$promises=aC.$$promises||true;
delete aC.$$inheritedValues;
aq.resolve(az)
}}function ap(aD){aC.$$failure=aD;
aq.reject(aD)
}if(E(ay.$$failure)){ap(ay.$$failure);
return aC
}if(ay.$$inheritedValues){P(az,D(ay.$$inheritedValues,an))
}q(aw,ay.$$promises);
if(ay.$$values){ax=P(az,D(ay.$$values,an));
aC.$$inheritedValues=D(ay.$$values,an);
at()
}else{if(ay.$$inheritedValues){aC.$$inheritedValues=D(ay.$$inheritedValues,an)
}ay.then(at,ap)
}for(var au=0,aA=am.length;
au<aA;
au+=3){if(ao.hasOwnProperty(am[au])){at()
}else{ar(am[au],am[au+1],am[au+2])
}}function ar(aD,aE,aJ){var aI=ab.defer(),aH=0;
function aG(aK){aI.reject(aK);
ap(aK)
}c(aJ,function(aK){if(aw.hasOwnProperty(aK)&&!ao.hasOwnProperty(aK)){aH++;
aw[aK].then(function(aL){az[aK]=aL;
if(!(--aH)){aF()
}},aG)
}});
if(!aH){aF()
}function aF(){if(E(aC.$$failure)){return
}try{aI.resolve(af.invoke(aE,aB,az));
aI.promise.then(function(aL){az[aD]=aL;
at()
},aG)
}catch(aK){aG(aK)
}}aw[aD]=aI.promise
}return aC
}
};
this.resolve=function(ai,ak,aj,ah){return this.study(ai)(ak,aj,ah)
}
}G.module("ui.router.util").service("$resolve",i);
C.$inject=["$http","$templateCache","$injector"];
function C(ab,Z,aa){this.fromConfig=function(ac,ae,ad){return(E(ac.template)?this.fromString(ac.template,ae):E(ac.templateUrl)?this.fromUrl(ac.templateUrl,ae):E(ac.templateProvider)?this.fromProvider(ac.templateProvider,ae,ad):null)
};
this.fromString=function(ac,ad){return w(ac)?ac(ad):ac
};
this.fromUrl=function(ac,ad){if(w(ac)){ac=ac(ad)
}if(ac==null){return null
}else{return ab.get(ac,{cache:Z,headers:{Accept:"text/html"}}).then(function(ae){return ae.data
})
}};
this.fromProvider=function(ae,ad,ac){return aa.invoke(ae,null,ac||{params:ad})
}
}G.module("ui.router.util").service("$templateFactory",C);
var b;
function L(ap,at,ac){at=q({params:{}},O(at)?at:{});
var ad=/([:*])([\w\[\]]+)|\{([\w\[\]]+)(?:\:\s*((?:[^{}\\]+|\\.|\{(?:[^{}\\]+|\\.)*\})+))?\}/g,al=/([:]?)([\w\[\].-]+)|\{([\w\[\].-]+)(?:\:\s*((?:[^{}\\]+|\\.|\{(?:[^{}\\]+|\\.)*\})+))?\}/g,aq="^",ai=0,am,an=this.segments=[],ah=ac?ac.params:{},ar=this.params=ac?ac.params.$$new():new b.ParamSet(),ae=[];
function ab(ax,aw,av,au){ae.push(ax);
if(ah[ax]){return ah[ax]
}if(!/^\w+([-.]+\w+)*(?:\[\])?$/.test(ax)){throw new Error("Invalid parameter name '"+ax+"' in pattern '"+ap+"'")
}if(ar[ax]){throw new Error("Duplicate parameter name '"+ax+"' in pattern '"+ap+"'")
}ar[ax]=new b.Param(ax,aw,av,au);
return ar[ax]
}function Z(ax,az,ay,aw){var av=["",""],au=ax.replace(/[\\\[\]\^$*+?.()|{}]/g,"\\$&");
if(!az){return au
}switch(ay){case false:av=["(",")"+(aw?"?":"")];
break;
case true:au=au.replace(/\/$/,"");
av=["(?:/(",")|/)?"];
break;
default:av=["("+ay+"|",")?"];
break
}return au+av[0]+az+av[1]
}this.source=ap;
function af(au,aw){var aB,aA,az,ay,av,ax;
aB=au[2]||au[3];
av=at.params[aB];
az=ap.substring(ai,au.index);
aA=aw?au[4]:au[4]||(au[1]=="*"?".*":null);
if(aA){ay=b.type(aA)||v(b.type("string"),{pattern:new RegExp(aA,at.caseInsensitive?"i":r)})
}return{id:aB,regexp:aA,segment:az,type:ay,cfg:av}
}var ak,ag,aa;
while((am=ad.exec(ap))){ak=af(am,false);
if(ak.segment.indexOf("?")>=0){break
}ag=ab(ak.id,ak.type,ak.cfg,"path");
aq+=Z(ak.segment,ag.type.pattern.source,ag.squash,ag.isOptional);
an.push(ak.segment);
ai=ad.lastIndex
}aa=ap.substring(ai);
var ao=aa.indexOf("?");
if(ao>=0){var aj=this.sourceSearch=aa.substring(ao);
aa=aa.substring(0,ao);
this.sourcePath=ap.substring(0,ai+ao);
if(aj.length>0){ai=0;
while((am=al.exec(aj))){ak=af(am,true);
ag=ab(ak.id,ak.type,ak.cfg,"search");
ai=ad.lastIndex
}}}else{this.sourcePath=ap;
this.sourceSearch=""
}aq+=Z(aa)+(at.strict===false?"/?":"")+"$";
an.push(aa);
this.regexp=new RegExp(aq,at.caseInsensitive?"i":r);
this.prefix=an[0];
this.$$paramNames=ae
}L.prototype.concat=function(ab,aa){var Z={caseInsensitive:b.caseInsensitive(),strict:b.strictMode(),squash:b.defaultSquashPolicy()};
return new L(this.sourcePath+ab+this.sourceSearch,q(Z,aa),this)
};
L.prototype.toString=function(){return this.source
};
L.prototype.exec=function(am,aj){var ac=this.regexp.exec(am);
if(!ac){return null
}aj=aj||{};
var Z=this.parameters(),ab=Z.length,ai=this.segments.length-1,al={},af,ae,ah,ag;
if(ai!==ac.length-1){throw new Error("Unbalanced capture group in route '"+this.source+"'")
}function ad(ao){function ar(at){return at.split("").reverse().join("")
}function an(at){return at.replace(/\\-/g,"-")
}var ap=ar(ao).split(/-(?!\\)/);
var aq=K(ap,ar);
return K(aq,an).reverse()
}var aa,ak;
for(af=0;
af<ai;
af++){ag=Z[af];
aa=this.params[ag];
ak=ac[af+1];
for(ae=0;
ae<aa.replace.length;
ae++){if(aa.replace[ae].from===ak){ak=aa.replace[ae].to
}}if(ak&&aa.array===true){ak=ad(ak)
}if(E(ak)){ak=aa.type.decode(ak)
}al[ag]=aa.value(ak)
}for(;
af<ab;
af++){ag=Z[af];
al[ag]=this.params[ag].value(aj[ag]);
aa=this.params[ag];
ak=aj[ag];
for(ae=0;
ae<aa.replace.length;
ae++){if(aa.replace[ae].from===ak){ak=aa.replace[ae].to
}}if(E(ak)){ak=aa.type.decode(ak)
}al[ag]=aa.value(ak)
}return al
};
L.prototype.parameters=function(Z){if(!E(Z)){return this.$$paramNames
}return this.params[Z]||null
};
L.prototype.validates=function(Z){return this.params.$$validates(Z)
};
L.prototype.format=function(Z){Z=Z||{};
var al=this.segments,ar=this.parameters(),ap=this.params;
if(!this.validates(Z)){return null
}var ao,ad=false,am=al.length-1,an=ar.length,af=al[0];
function ac(au){return encodeURIComponent(au).replace(/-/g,function(av){return"%5C%"+av.charCodeAt(0).toString(16).toUpperCase()
})
}for(ao=0;
ao<an;
ao++){var ag=ao<am;
var at=ar[ao],ab=ap[at],ai=ab.value(Z[at]);
var ae=ab.isOptional&&ab.type.equals(ab.value(),ai);
var aq=ae?ab.squash:false;
var aa=ab.type.encode(ai);
if(ag){var ak=al[ao+1];
var ah=ao+1===am;
if(aq===false){if(aa!=null){if(F(aa)){af+=K(aa,ac).join("-")
}else{af+=encodeURIComponent(aa)
}}af+=ak
}else{if(aq===true){var aj=af.match(/\/$/)?/\/?(.*)/:/(.*)/;
af+=ak.match(aj)[1]
}else{if(o(aq)){af+=aq+ak
}}}if(ah&&ab.squash===true&&af.slice(-1)==="/"){af=af.slice(0,-1)
}}else{if(aa==null||(ae&&aq!==false)){continue
}if(!F(aa)){aa=[aa]
}if(aa.length===0){continue
}aa=K(aa,encodeURIComponent).join("&"+at+"=");
af+=(ad?"&":"?")+(at+"="+aa);
ad=true
}}return af
};
function S(Z){q(this,Z)
}S.prototype.is=function(aa,Z){return true
};
S.prototype.encode=function(aa,Z){return aa
};
S.prototype.decode=function(aa,Z){return aa
};
S.prototype.equals=function(aa,Z){return aa==Z
};
S.prototype.$subPattern=function(){var Z=this.pattern.toString();
return Z.substr(1,Z.length-2)
};
S.prototype.pattern=/.*/;
S.prototype.toString=function(){return"{Type:"+this.name+"}"
};
S.prototype.$normalize=function(Z){return this.is(Z)?Z:this.decode(Z)
};
S.prototype.$asArray=function(aa,Z){if(!aa){return this
}if(aa==="auto"&&!Z){throw new Error("'auto' array mode is for query parameters only")
}function ab(af,ah){function ad(ak,al){return function(){return ak[al].apply(ak,arguments)
}
}function aj(ak){return F(ak)?ak:(E(ak)?[ak]:[])
}function ae(ak){switch(ak.length){case 0:return r;
case 1:return ah==="auto"?ak[0]:ak;
default:return ak
}}function ag(ak){return !ak
}function ac(am,al){return function ak(ao){if(F(ao)&&ao.length===0){return ao
}ao=aj(ao);
var an=K(ao,am);
if(al===true){return M(an,ag).length===0
}return ae(an)
}
}function ai(al){return function ak(ap,ao){var aq=aj(ap),an=aj(ao);
if(aq.length!==an.length){return false
}for(var am=0;
am<aq.length;
am++){if(!al(aq[am],an[am])){return false
}}return true
}
}this.encode=ac(ad(af,"encode"));
this.decode=ac(ad(af,"decode"));
this.is=ac(ad(af,"is"),true);
this.equals=ai(ad(af,"equals"));
this.pattern=af.pattern;
this.$normalize=ac(ad(af,"$normalize"));
this.name=af.name;
this.$arrayMode=ah
}return new ab(this,aa)
};
function n(){b=this;
var ag=false,an=true,ad=false;
function ai(ap){return ap!=null?ap.toString().replace(/~/g,"~~").replace(/\//g,"~2F"):ap
}function am(ap){return ap!=null?ap.toString().replace(/~2F/g,"/").replace(/~~/g,"~"):ap
}var aj={},aa=true,ak=[],ah,af={string:{encode:ai,decode:am,is:function(ap){return ap==null||!E(ap)||typeof ap==="string"
},pattern:/[^/]*/},"int":{encode:ai,decode:function(ap){return parseInt(ap,10)
},is:function(ap){return E(ap)&&this.decode(ap.toString())===ap
},pattern:/\d+/},bool:{encode:function(ap){return ap?1:0
},decode:function(ap){return parseInt(ap,10)!==0
},is:function(ap){return ap===true||ap===false
},pattern:/0|1/},date:{encode:function(ap){if(!this.is(ap)){return r
}return[ap.getFullYear(),("0"+(ap.getMonth()+1)).slice(-2),("0"+ap.getDate()).slice(-2)].join("-")
},decode:function(aq){if(this.is(aq)){return aq
}var ap=this.capture.exec(aq);
return ap?new Date(ap[1],ap[2]-1,ap[3]):r
},is:function(ap){return ap instanceof Date&&!isNaN(ap.valueOf())
},equals:function(aq,ap){return this.is(aq)&&this.is(ap)&&aq.toISOString()===ap.toISOString()
},pattern:/[0-9]{4}-(?:0[1-9]|1[0-2])-(?:0[1-9]|[1-2][0-9]|3[0-1])/,capture:/([0-9]{4})-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])/},json:{encode:G.toJson,decode:G.fromJson,is:G.isObject,equals:G.equals,pattern:/[^/]*/},any:{encode:G.identity,decode:G.identity,equals:G.equals,pattern:/.*/}};
function al(){return{strict:an,caseInsensitive:ag}
}function ae(ap){return(w(ap)||(F(ap)&&w(ap[ap.length-1])))
}n.$$getDefaultValue=function(ap){if(!ae(ap.value)){return ap.value
}if(!ah){throw new Error("Injectable functions cannot be called at configuration time")
}return ah.invoke(ap.value)
};
this.caseInsensitive=function(ap){if(E(ap)){ag=ap
}return ag
};
this.strictMode=function(ap){if(E(ap)){an=ap
}return an
};
this.defaultSquashPolicy=function(ap){if(!E(ap)){return ad
}if(ap!==true&&ap!==false&&!o(ap)){throw new Error("Invalid squash policy: "+ap+". Valid policies: false, true, arbitrary-string")
}ad=ap;
return ap
};
this.compile=function(aq,ap){return new L(aq,q(al(),ap))
};
this.isMatcher=function(aq){if(!O(aq)){return false
}var ap=true;
c(L.prototype,function(at,ar){if(w(at)){ap=ap&&(E(aq[ar])&&w(aq[ar]))
}});
return ap
};
this.type=function(ap,aq,ar){if(!E(aq)){return aj[ap]
}if(aj.hasOwnProperty(ap)){throw new Error("A type named '"+ap+"' has already been defined.")
}aj[ap]=new S(q({name:ap},aq));
if(ar){ak.push({name:ap,def:ar});
if(!aa){ab()
}}return this
};
function ab(){while(ak.length){var ap=ak.shift();
if(ap.pattern){throw new Error("You cannot override a type's .pattern at runtime.")
}G.extend(aj[ap.name],ah.invoke(ap.def))
}}c(af,function(aq,ap){aj[ap]=new S(q({name:ap},aq))
});
aj=v(aj,{});
this.$get=["$injector",function(ap){ah=ap;
aa=false;
ab();
c(af,function(ar,aq){if(!aj[aq]){aj[aq]=new S(ar)
}});
return this
}];
this.Param=function Z(ar,aC,aw,aE){var aG=this;
aw=aq(aw);
aC=aB(aw,aC,aE);
var az=ap();
aC=az?aC.$asArray(az,aE==="search"):aC;
if(aC.name==="string"&&!az&&aE==="path"&&aw.value===r){aw.value=""
}var ax=aw.value!==r;
var ay=aA(aw,ax);
var av=aF(aw,az,ax,ay);
function aq(aH){var aJ=O(aH)?f(aH):[];
var aI=z(aJ,"value")===-1&&z(aJ,"type")===-1&&z(aJ,"squash")===-1&&z(aJ,"array")===-1;
if(aI){aH={value:aH}
}aH.$$fn=ae(aH.value)?aH.value:function(){return aH.value
};
return aH
}function aB(aI,aJ,aH){if(aI.type&&aJ){throw new Error("Param '"+ar+"' has two type configurations.")
}if(aJ){return aJ
}if(!aI.type){return(aH==="config"?aj.any:aj.string)
}if(G.isString(aI.type)){return aj[aI.type]
}if(aI.type instanceof S){return aI.type
}return new S(aI.type)
}function ap(){var aI={array:(aE==="search"?"auto":false)};
var aH=ar.match(/\[\]$/)?{array:true}:{};
return q(aI,aH,aw).array
}function aA(aI,aH){var aJ=aI.squash;
if(!aH||aJ===false){return false
}if(!E(aJ)||aJ==null){return ad
}if(aJ===true||o(aJ)){return aJ
}throw new Error("Invalid squash policy: '"+aJ+"'. Valid policies: false, true, or arbitrary string")
}function aF(aK,aJ,aI,aN){var aL,aH,aM=[{from:"",to:(aI||aJ?r:"")},{from:null,to:(aI||aJ?r:"")}];
aL=F(aK.replace)?aK.replace:[];
if(o(aN)){aL.push({from:aN,to:r})
}aH=K(aL,function(aO){return aO.from
});
return M(aM,function(aO){return z(aH,aO.from)===-1
}).concat(aL)
}function au(){if(!ah){throw new Error("Injectable functions cannot be called at configuration time")
}var aH=ah.invoke(aw.$$fn);
if(aH!==null&&aH!==r&&!aG.type.is(aH)){throw new Error("Default value ("+aH+") for parameter '"+aG.id+"' is not an instance of Type ("+aG.type.name+")")
}return aH
}function aD(aJ){function aI(aK){return function(aL){return aL.from===aK
}
}function aH(aL){var aK=K(M(aG.replace,aI(aL)),function(aM){return aM.to
});
return aK.length?aK[0]:aL
}aJ=aH(aJ);
return !E(aJ)?au():aG.type.$normalize(aJ)
}function at(){return"{Param:"+ar+" "+aC+" squash: '"+ay+"' optional: "+ax+"}"
}q(this,{id:ar,type:aC,location:aE,array:az,squash:ay,replace:av,isOptional:ax,value:aD,dynamic:r,config:aw,toString:at})
};
function ac(ap){q(this,ap||{})
}ac.prototype={$$new:function(){return v(this,q(new ac(),{$$parent:this}))
},$$keys:function(){var ar=[],ap=[],aq=this,at=f(ac.prototype);
while(aq){ap.push(aq);
aq=aq.$$parent
}ap.reverse();
c(ap,function(au){c(f(au),function(av){if(z(ar,av)===-1&&z(at,av)===-1){ar.push(av)
}})
});
return ar
},$$values:function(ar){var aq={},ap=this;
c(ap.$$keys(),function(at){aq[at]=ap[at].value(ar&&ar[at])
});
return aq
},$$equals:function(ap,at){var ar=true,aq=this;
c(aq.$$keys(),function(av){var aw=ap&&ap[av],au=at&&at[av];
if(!aq[av].type.equals(aw,au)){ar=false
}});
return ar
},$$validates:function ao(au){var aq=this.$$keys(),ap,aw,at,av,ar;
for(ap=0;
ap<aq.length;
ap++){aw=this[aq[ap]];
at=au[aq[ap]];
if((at===r||at===null)&&aw.isOptional){break
}av=aw.type.$normalize(at);
if(!aw.type.is(av)){return false
}ar=aw.type.encode(av);
if(G.isString(ar)&&!aw.type.pattern.exec(ar)){return false
}}return true
},$$parent:r};
this.ParamSet=ac
}G.module("ui.router.util").provider("$urlMatcherFactory",n);
G.module("ui.router.util").run(["$urlMatcherFactory",function(Z){}]);
g.$inject=["$locationProvider","$urlMatcherFactoryProvider"];
function g(af,ae){var ai=[],aa=null,ah=false,ab;
function ac(aj){var ak=/^\^((?:\\[^a-zA-Z0-9]|[^\\\[\]\^$*+?.()|{}]+)*)/.exec(aj.source);
return(ak!=null)?ak[1].replace(/\\(.)/g,"$1"):""
}function ad(ak,aj){return ak.replace(/\$(\$|\d{1,2})/,function(al,am){return aj[am==="$"?0:Number(am)]
})
}this.rule=function(aj){if(!w(aj)){throw new Error("'rule' must be a function")
}ai.push(aj);
return this
};
this.otherwise=function(aj){if(o(aj)){var ak=aj;
aj=function(){return ak
}
}else{if(!w(aj)){throw new Error("'rule' must be a function")
}}aa=aj;
return this
};
function Z(am,al,ak){if(!ak){return false
}var aj=am.invoke(al,al,{$match:ak});
return E(aj)?aj:true
}this.when=function(am,ak){var ap,an=o(ak);
if(o(am)){am=ae.compile(am)
}if(!an&&!w(ak)&&!F(ak)){throw new Error("invalid 'handler' in when()")
}var al={matcher:function(ar,aq){if(an){ap=ae.compile(aq);
aq=["$match",function(at){return ap.format(at)
}]
}return q(function(at,au){return Z(at,aq,ar.exec(au.path(),au.search()))
},{prefix:o(ar.prefix)?ar.prefix:""})
},regex:function(ar,aq){if(ar.global||ar.sticky){throw new Error("when() RegExp must not be global or sticky")
}if(an){ap=aq;
aq=["$match",function(at){return ad(ap,at)
}]
}return q(function(at,au){return Z(at,aq,ar.exec(au.path()))
},{prefix:ac(ar)})
}};
var aj={matcher:ae.isMatcher(am),regex:am instanceof RegExp};
for(var ao in aj){if(aj[ao]){return this.rule(al[ao](am,ak))
}}throw new Error("invalid 'what' in when()")
};
this.deferIntercept=function(aj){if(aj===r){aj=true
}ah=aj
};
this.$get=ag;
ag.$inject=["$location","$rootScope","$injector","$browser","$sniffer"];
function ag(ap,aq,at,au,ak){var aj=au.baseHref(),ar=ap.url(),am;
function ao(av,aw,ax){if(aj==="/"){return av
}if(aw){return aj.slice(0,-1)+av
}if(ax){return aj.slice(1)+av
}return av
}function an(aw){if(aw&&aw.defaultPrevented){return
}var ay=am&&ap.url()===am;
am=r;
function av(aB){var aA=aB(at,ap);
if(!aA){return false
}if(o(aA)){ap.replace().url(aA)
}return true
}var az=ai.length,ax;
for(ax=0;
ax<az;
ax++){if(av(ai[ax])){return
}}if(aa){av(aa)
}}function al(){ab=ab||aq.$on("$locationChangeSuccess",an);
return ab
}if(!ah){al()
}return{sync:function(){an()
},listen:function(){return al()
},update:function(av){if(av){ar=ap.url();
return
}if(ap.url()===ar){return
}ap.url(ar);
ap.replace()
},push:function(ax,ay,aw){var av=ax.format(ay||{});
if(av!==null&&ay&&ay["#"]){av+="#"+ay["#"]
}ap.url(av);
am=aw&&aw.$$avoidResync?ap.url():r;
if(aw&&aw.replace){ap.replace()
}},href:function(aA,aB,ax){if(!aA.validates(aB)){return null
}var az=af.html5Mode();
if(G.isObject(az)){az=az.enabled
}az=az&&ak.history;
var aw=aA.format(aB);
ax=ax||{};
if(!az&&aw!==null){aw="#"+af.hashPrefix()+aw
}if(aw!==null&&aB&&aB["#"]){aw+="#"+aB["#"]
}aw=ao(aw,az,ax.absolute);
if(!ax.absolute||!aw){return aw
}var ay=(!az&&aw?"/":""),av=ap.port();
av=(av===80||av===443?"":":"+av);
return[ap.protocol(),"://",ap.host(),av,ay,aw].join("")
}}
}}G.module("ui.router.router").provider("$urlRouter",g);
s.$inject=["$urlRouterProvider","$urlMatcherFactoryProvider"];
function s(ah,ar){var al,ag={},aq,am={},ad="abstract";
var aj={parent:function(au){if(E(au.parent)&&au.parent){return ac(au.parent)
}var at=/^(.+)\.[^.]+$/.exec(au.name);
return at?ac(at[1]):al
},data:function(at){if(at.parent&&at.parent.data){at.data=at.self.data=v(at.parent.data,at.data)
}return at.data
},url:function(av){var au=av.url,at={params:av.params||{}};
if(o(au)){if(au.charAt(0)=="^"){return ar.compile(au.substring(1),at)
}return(av.parent.navigable||al).url.concat(au,at)
}if(!au||ar.isMatcher(au)){return au
}throw new Error("Invalid url '"+au+"' in state '"+av+"'")
},navigable:function(at){return at.url?at:(at.parent?at.parent.navigable:null)
},ownParams:function(at){var au=at.url&&at.url.params||new b.ParamSet();
c(at.params||{},function(av,aw){if(!au[aw]){au[aw]=new b.Param(aw,null,av,"config")
}});
return au
},params:function(at){var au=A(at.ownParams,at.ownParams.$$keys());
return at.parent&&at.parent.params?q(at.parent.params.$$new(),au):new b.ParamSet()
},views:function(au){var at={};
c(E(au.views)?au.views:{"":au},function(av,aw){if(aw.indexOf("@")<0){aw+="@"+au.parent.name
}at[aw]=av
});
return at
},path:function(at){return at.parent?at.parent.path.concat(at):[]
},includes:function(au){var at=au.parent?q({},au.parent.includes):{};
at[au.name]=true;
return at
},$delegates:{}};
function ak(at){return at.indexOf(".")===0||at.indexOf("^")===0
}function ac(aw,av){if(!aw){return r
}var az=o(aw),au=az?aw:aw.name,aC=ak(au);
if(aC){if(!av){throw new Error("No reference point given for path '"+au+"'")
}av=ac(av);
var aB=au.split("."),ax=0,aA=aB.length,ay=av;
for(;
ax<aA;
ax++){if(aB[ax]===""&&ax===0){ay=av;
continue
}if(aB[ax]==="^"){if(!ay.parent){throw new Error("Path '"+au+"' not valid for state '"+av.name+"'")
}ay=ay.parent;
continue
}break
}aB=aB.slice(ax).join(".");
au=ay.name+(ay.name&&aB?".":"")+aB
}var at=ag[au];
if(at&&(az||(!az&&(at===aw||at.self===aw)))){return at
}return r
}function an(au,at){if(!am[au]){am[au]=[]
}am[au].push(at)
}function ai(at){var au=am[at]||[];
while(au.length){aa(au.shift())
}}function aa(av){av=v(av,{self:av,resolve:av.resolve||{},toString:function(){return this.name
}});
var at=av.name;
if(!o(at)||at.indexOf("@")>=0){throw new Error("State must have a valid name")
}if(ag.hasOwnProperty(at)){throw new Error("State '"+at+"' is already defined")
}var aw=(at.indexOf(".")!==-1)?at.substring(0,at.lastIndexOf(".")):(o(av.parent))?av.parent:(O(av.parent)&&o(av.parent.name))?av.parent.name:"";
if(aw&&!ag[aw]){return an(aw,av.self)
}for(var au in aj){if(w(aj[au])){av[au]=aj[au](av,aj.$delegates[au])
}}ag[at]=av;
if(!av[ad]&&av.url){ah.when(av.url,["$match","$stateParams",function(ax,ay){if(aq.$current.navigable!=av||!J(ax,ay)){aq.transitionTo(av,ax,{inherit:true,location:false})
}}])
}ai(at);
return av
}function ap(at){return at.indexOf("*")>-1
}function ab(ax){var aw=ax.split("."),au=aq.$current.name.split(".");
for(var av=0,at=aw.length;
av<at;
av++){if(aw[av]==="*"){au[av]="*"
}}if(aw[0]==="**"){au=au.slice(z(au,aw[1]));
au.unshift("**")
}if(aw[aw.length-1]==="**"){au.splice(z(au,aw[aw.length-2])+1,Number.MAX_VALUE);
au.push("**")
}if(aw.length!=au.length){return false
}return au.join("")===aw.join("")
}al=aa({name:"",url:"^",views:null,"abstract":true});
al.navigable=null;
this.decorator=Z;
function Z(at,au){if(o(at)&&!E(au)){return aj[at]
}if(!w(au)||!o(at)){return this
}if(aj[at]&&!aj.$delegates[at]){aj.$delegates[at]=aj[at]
}aj[at]=au;
return this
}this.state=ae;
function ae(at,au){if(O(at)){au=at
}else{au.name=at
}aa(au);
return this
}this.$get=af;
af.$inject=["$rootScope","$q","$view","$injector","$resolve","$stateParams","$urlRouter","$location","$urlMatcherFactory"];
function af(ay,aK,aG,aH,au,aM,aN,ax,aJ){var az=aK.reject(new Error("transition superseded"));
var av=aK.reject(new Error("transition prevented"));
var aA=aK.reject(new Error("transition aborted"));
var aB=aK.reject(new Error("transition failed"));
function aL(aT,aR,aS,aQ){var aP=ay.$broadcast("$stateNotFound",aT,aR,aS);
if(aP.defaultPrevented){aN.update();
return aA
}if(!aP.retry){return null
}if(aQ.$retry){aN.update();
return aB
}var aO=aq.transition=aK.when(aP.retry);
aO.then(function(){if(aO!==aq.transition){return az
}aT.options.$retry=true;
return aq.transitionTo(aT.to,aT.toParams,aT.options)
},function(){return aA
});
aN.update();
return aO
}al.locals={resolve:null,globals:{$stateParams:{}}};
aq={params:{},current:al.self,$current:al,transition:null};
aq.reload=function aE(aO){return aq.transitionTo(aq.current,aM,{reload:aO||true,inherit:false,notify:true})
};
aq.go=function aD(aQ,aP,aO){return aq.transitionTo(aQ,aP,q({inherit:true,relative:aq.$current},aO))
};
aq.transitionTo=function at(aQ,aX,aS){aX=aX||{};
aS=q({location:true,inherit:false,relative:null,notify:true,reload:false,$retry:false},aS||{});
var a3=aq.$current,a4=aq.params,aU=a3.path;
var aY,a0=ac(aQ,aS.relative);
var aO=aX["#"];
if(!E(a0)){var aW={to:aQ,toParams:aX,options:aS};
var aP=aL(aW,a3.self,a4,aS);
if(aP){return aP
}aQ=aW.to;
aX=aW.toParams;
aS=aW.options;
a0=ac(aQ,aS.relative);
if(!E(a0)){if(!aS.relative){throw new Error("No such state '"+aQ+"'")
}throw new Error("Could not resolve '"+aQ+"' from state '"+aS.relative+"'")
}}if(a0[ad]){throw new Error("Cannot transition to abstract state '"+aQ+"'")
}if(aS.inherit){aX=R(aM,aX||{},aq.$current,a0)
}if(!a0.params.$$validates(aX)){return aB
}aX=a0.params.$$values(aX);
aQ=a0;
var aZ=aQ.path;
var a5=0,aT=aZ[a5],aV=al.locals,aR=[];
if(!aS.reload){while(aT&&aT===aU[a5]&&aT.ownParams.$$equals(aX,a4)){aV=aR[a5]=aT.locals;
a5++;
aT=aZ[a5]
}}else{if(o(aS.reload)||O(aS.reload)){if(O(aS.reload)&&!aS.reload.name){throw new Error("Invalid reload state object")
}var a7=aS.reload===true?aU[0]:ac(aS.reload);
if(aS.reload&&!a7){throw new Error("No such reload state '"+(o(aS.reload)?aS.reload:aS.reload.name)+"'")
}while(aT&&aT===aU[a5]&&aT!==a7){aV=aR[a5]=aT.locals;
a5++;
aT=aZ[a5]
}}}if(ao(aQ,aX,a3,a4,aV,aS)){if(aO){aX["#"]=aO
}aq.params=aX;
V(aq.params,aM);
V(j(aQ.params.$$keys(),aM),aQ.locals.globals.$stateParams);
if(aS.location&&aQ.navigable&&aQ.navigable.url){aN.push(aQ.navigable.url,aX,{$$avoidResync:true,replace:aS.location==="replace"});
aN.update(true)
}aq.transition=null;
return aK.when(aq.current)
}aX=j(aQ.params.$$keys(),aX||{});
if(aO){aX["#"]=aO
}if(aS.notify){if(ay.$broadcast("$stateChangeStart",aQ.self,aX,a3.self,a4,aS).defaultPrevented){ay.$broadcast("$stateChangeCancel",aQ.self,aX,a3.self,a4);
if(aq.transition==null){aN.update()
}return av
}}var a1=aK.when(aV);
for(var a2=a5;
a2<aZ.length;
a2++,aT=aZ[a2]){aV=aR[a2]=v(aV);
a1=aC(aT,aX,aT===aQ,a1,aV,aS)
}var a6=aq.transition=a1.then(function(){var a8,ba,a9;
if(aq.transition!==a6){return az
}for(a8=aU.length-1;
a8>=a5;
a8--){a9=aU[a8];
if(a9.self.onExit){aH.invoke(a9.self.onExit,a9.self,a9.locals.globals)
}a9.locals=null
}for(a8=a5;
a8<aZ.length;
a8++){ba=aZ[a8];
ba.locals=aR[a8];
if(ba.self.onEnter){aH.invoke(ba.self.onEnter,ba.self,ba.locals.globals)
}}if(aq.transition!==a6){return az
}aq.$current=aQ;
aq.current=aQ.self;
aq.params=aX;
V(aq.params,aM);
aq.transition=null;
if(aS.location&&aQ.navigable){aN.push(aQ.navigable.url,aQ.navigable.locals.globals.$stateParams,{$$avoidResync:true,replace:aS.location==="replace"})
}if(aS.notify){ay.$broadcast("$stateChangeSuccess",aQ.self,aX,a3.self,a4)
}aN.update(true);
return aq.current
},function(a8){if(aq.transition!==a6){return az
}aq.transition=null;
aY=ay.$broadcast("$stateChangeError",aQ.self,aX,a3.self,a4,a8);
if(!aY.defaultPrevented){aN.update()
}return aK.reject(a8)
});
return a6
};
aq.is=function aw(aP,aR,aO){aO=q({relative:aq.$current},aO||{});
var aQ=ac(aP,aO.relative);
if(!E(aQ)){return r
}if(aq.$current!==aQ){return false
}return aR?J(aQ.params.$$values(aR),aM):true
};
aq.includes=function aF(aP,aR,aO){aO=q({relative:aq.$current},aO||{});
if(o(aP)&&ap(aP)){if(!ab(aP)){return false
}aP=aq.$current.name
}var aQ=ac(aP,aO.relative);
if(!E(aQ)){return r
}if(!E(aq.$current.includes[aQ.name])){return false
}return aR?J(aQ.params.$$values(aR),aM,f(aR)):true
};
aq.href=function aI(aP,aS,aO){aO=q({lossy:true,inherit:true,absolute:false,relative:aq.$current},aO||{});
var aQ=ac(aP,aO.relative);
if(!E(aQ)){return null
}if(aO.inherit){aS=R(aM,aS||{},aq.$current,aQ)
}var aR=(aQ&&aO.lossy)?aQ.navigable:aQ;
if(!aR||aR.url===r||aR.url===null){return null
}return aN.href(aR.url,j(aQ.params.$$keys().concat("#"),aS||{}),{absolute:aO.absolute})
};
aq.get=function(aP,aO){if(arguments.length===0){return K(f(ag),function(aR){return ag[aR].self
})
}var aQ=ac(aP,aO||aq.$current);
return(aQ&&aQ.self)?aQ.self:null
};
function aC(aO,aR,aV,aS,aT,aX){var aW=(aV)?aR:j(aO.params.$$keys(),aR);
var aQ={$stateParams:aW};
aT.resolve=au.resolve(aO.resolve,aQ,aT.resolve,aO);
var aU=[aT.resolve.then(function(aY){aT.globals=aY
})];
if(aS){aU.push(aS)
}function aP(){var aY=[];
c(aO.views,function(aZ,a0){var a1=(aZ.resolve&&aZ.resolve!==aO.resolve?aZ.resolve:{});
a1.$template=[function(){return aG.load(a0,{view:aZ,locals:aT.globals,params:aW,notify:aX.notify})||""
}];
aY.push(au.resolve(a1,aT.globals,aT.resolve,aO).then(function(a2){if(w(aZ.controllerProvider)||F(aZ.controllerProvider)){var a3=G.extend({},a1,aT.globals);
a2.$$controller=aH.invoke(aZ.controllerProvider,null,a3)
}else{a2.$$controller=aZ.controller
}a2.$$state=aO;
a2.$$controllerAs=aZ.controllerAs;
aT[a0]=a2
}))
});
return aK.all(aY).then(function(){return aT.globals
})
}return aK.all(aU).then(aP).then(function(aY){return aT
})
}return aq
}function ao(az,aw,ay,av,ax,au){function at(aE,aD,aC){function aG(aH){return aE.params[aH].location!="search"
}var aB=aE.params.$$keys().filter(aG);
var aA=A.apply({},[aE.params].concat(aB));
var aF=new b.ParamSet(aA);
return aF.$$equals(aD,aC)
}if(!au.reload&&az===ay&&(ax===ay.locals||(az.self.reloadOnSearch===false&&at(ay,av,aw)))){return true
}}}G.module("ui.router.state").factory("$stateParams",function(){return{}
}).provider("$state",s);
Y.$inject=[];
function Y(){this.$get=Z;
Z.$inject=["$rootScope","$templateFactory"];
function Z(aa,ac){return{load:function ab(af,ae){var ad,ag={template:null,controller:null,view:null,locals:null,notify:true,async:true,params:{}};
ae=q(ag,ae);
if(ae.view){ad=ac.fromConfig(ae.view,ae.params,ae.locals)
}return ad
}}
}}G.module("ui.router.state").provider("$view",Y);
function m(){var Z=false;
this.useAnchorScroll=function(){Z=true
};
this.$get=["$anchorScroll","$timeout",function(ab,aa){if(Z){return ab
}return function(ac){return aa(function(){ac[0].scrollIntoView()
},0,false)
}
}]
}G.module("ui.router.state").provider("$uiViewScroll",m);
var a=G.version.major;
var e=G.version.minor;
u.$inject=["$state","$injector","$uiViewScroll","$interpolate"];
function u(Z,ah,ab,ac){function ad(){return(ah.has)?function(aj){return ah.has(aj)?ah.get(aj):null
}:function(aj){try{return ah.get(aj)
}catch(ak){return null
}}
}var ae=ad(),ai=ae("$animator"),af=ae("$animate");
function aa(al,am){var an={enter:function(ap,aq,ao){aq.after(ap);
ao()
},leave:function(ap,ao){ap.remove();
ao()
}};
if(!!al.noanimation){return an
}function ak(ao){if(a===1&&e>=4){return !!af.enabled(ao)
}if(a===1&&e>=2){return !!af.enabled()
}return(!!ai)
}if(af){return{enter:function(ap,aq,ao){if(!ak(ap)){an.enter(ap,aq,ao)
}else{if(G.version.minor>2){af.enter(ap,null,aq).then(ao)
}else{af.enter(ap,null,aq,ao)
}}},leave:function(ap,ao){if(!ak(ap)){an.leave(ap,ao)
}else{if(G.version.minor>2){af.leave(ap).then(ao)
}else{af.leave(ap,ao)
}}}}
}if(ai){var aj=ai&&ai(am,al);
return{enter:function(ap,aq,ao){aj.enter(ap,null,aq);
ao()
},leave:function(ap,ao){aj.leave(ap);
ao()
}}
}return an
}var ag={restrict:"ECA",terminal:true,priority:400,transclude:"element",compile:function(ak,al,aj){return function(aw,av,at){var an,aq,ay,am,au=at.onload||"",ao=at.autoscroll,ap=aa(at,aw);
aw.$on("$stateChangeSuccess",function(){ax(false)
});
ax(true);
function ar(){var aB=an;
var aA=ay;
if(aA){aA._willBeDestroyed=true
}function az(){if(aB){aB.remove()
}if(aA){aA.$destroy()
}}if(aq){ap.leave(aq,function(){az();
an=null
});
an=aq
}else{az();
an=null
}aq=null;
ay=null
}function ax(aD){var aB,az=y(aw,at,av,ac),aA=az&&Z.$current&&Z.$current.locals[az];
if(!aD&&aA===am||aw._willBeDestroyed){return
}aB=aw.$new();
am=Z.$current.locals[az];
aB.$emit("$viewContentLoading",az);
var aC=aj(aB,function(aF){ap.enter(aF,av,function aE(){if(ay){ay.$emit("$viewContentAnimationEnded")
}if(G.isDefined(ao)&&!ao||aw.$eval(ao)){ab(aF)
}});
ar()
});
aq=aC;
ay=aB;
ay.$emit("$viewContentLoaded",az);
ay.$eval(au)
}}
}};
return ag
}X.$inject=["$compile","$controller","$state","$interpolate"];
function X(ab,Z,aa,ac){return{restrict:"ECA",priority:-400,compile:function(ad){var ae=ad.html();
return function(aj,ag,ai){var am=aa.$current,ah=y(aj,ai,ag,ac),al=am&&am.locals[ah];
if(!al){return
}ag.data("$uiView",{name:ah,state:al.$$state});
ag.html(al.$template?al.$template:ae);
var ak=ab(ag.contents());
if(al.$$controller){al.$scope=aj;
al.$element=ag;
var af=Z(al.$$controller,al);
if(al.$$controllerAs){aj[al.$$controllerAs]=af
}ag.data("$ngControllerController",af);
ag.children().data("$ngControllerController",af)
}ak(aj)
}
}}
}function y(ad,ab,ac,ae){var aa=ae(ab.uiView||ab.name||"")(ad);
var Z=ac.inheritedData("$uiView");
return aa.indexOf("@")>=0?aa:(aa+"@"+(Z?Z.state.name:""))
}G.module("ui.router.state").directive("uiView",u);
G.module("ui.router.state").directive("uiView",X);
function I(ab,ac){var aa=ab.match(/^\s*({[^}]*})\s*$/),Z;
if(aa){ab=ac+"("+aa[1]+")"
}Z=ab.replace(/\n/g," ").match(/^([^(]+?)\s*(\((.*)\))?$/);
if(!Z||Z.length!==4){throw new Error("Invalid state ref '"+ab+"'")
}return{state:Z[1],paramExpr:Z[3]||null}
}function W(aa){var Z=aa.parent().inheritedData("$uiView");
if(Z&&Z.state&&Z.state.name){return Z.state
}}function l(Z){var ab=Object.prototype.toString.call(Z.prop("href"))==="[object SVGAnimatedString]";
var aa=Z[0].nodeName==="FORM";
return{attr:aa?"action":(ab?"xlink:href":"href"),isAnchor:Z.prop("tagName").toUpperCase()==="A",clickable:!aa}
}function d(ac,aa,Z,ab,ad){return function(ah){var af=ah.which||ah.button,ag=ad();
if(!(af>1||ah.ctrlKey||ah.metaKey||ah.shiftKey||ac.attr("target"))){var ai=Z(function(){aa.go(ag.state,ag.params,ag.options)
});
ah.preventDefault();
var ae=ab.isAnchor&&!ag.href?1:0;
ah.preventDefault=function(){if(ae--<=0){Z.cancel(ai)
}}
}}
}function x(aa,Z){return{relative:W(aa)||Z.$current,inherit:true}
}p.$inject=["$state","$timeout"];
function p(aa,Z){return{restrict:"A",require:["?^uiSrefActive","?^uiSrefActiveEq"],link:function(aj,ag,ai,ab){var ad=I(ai.uiSref,aa.current.name);
var ac={state:ad.state,href:null,params:null};
var ah=l(ag);
var ae=ab[1]||ab[0];
ac.options=q(x(ag,aa),ai.uiSrefOpts?aj.$eval(ai.uiSrefOpts):{});
var af=function(ak){if(ak){ac.params=G.copy(ak)
}ac.href=aa.href(ad.state,ac.params,ac.options);
if(ae){ae.$$addStateInfo(ad.state,ac.params)
}if(ac.href!==null){ai.$set(ah.attr,ac.href)
}};
if(ad.paramExpr){aj.$watch(ad.paramExpr,function(ak){if(ak!==ac.params){af(ak)
}},true);
ac.params=G.copy(aj.$eval(ad.paramExpr))
}af();
if(!ah.clickable){return
}ag.bind("click",d(ag,aa,Z,ah,function(){return ac
}))
}}
}U.$inject=["$state","$timeout"];
function U(aa,Z){return{restrict:"A",require:["?^uiSrefActive","?^uiSrefActiveEq"],link:function(ak,ae,aj,ab){var ah=l(ae);
var ad=ab[1]||ab[0];
var ai=[aj.uiState,aj.uiStateParams||null,aj.uiStateOpts||null];
var ag="["+ai.map(function(al){return al||"null"
}).join(", ")+"]";
var ac={state:null,params:null,options:null,href:null};
function af(al){ac.state=al[0];
ac.params=al[1];
ac.options=al[2];
ac.href=aa.href(ac.state,ac.params,ac.options);
if(ad){ad.$$addStateInfo(ac.state,ac.params)
}if(ac.href){aj.$set(ah.attr,ac.href)
}}ak.$watch(ag,af,true);
af(ak.$eval(ag));
if(!ah.clickable){return
}ae.bind("click",d(ae,aa,Z,ah,function(){return ac
}))
}}
}T.$inject=["$state","$stateParams","$interpolate"];
function T(Z,aa,ab){return{restrict:"A",controller:["$scope","$element","$attrs","$timeout",function(ap,aq,an,ae){var ar=[],ad={},ah,ac;
ah=ab(an.uiSrefActiveEq||"",false)(ap);
try{ac=ap.$eval(an.uiSrefActive)
}catch(am){}ac=ac||ab(an.uiSrefActive||"",false)(ap);
if(O(ac)){c(ac,function(au,at){if(o(au)){var av=I(au,Z.current.name);
ag(av.state,ap.$eval(av.paramExpr),at)
}})
}this.$$addStateInfo=function(at,au){if(O(ac)&&ar.length>0){return
}ag(at,au,ac);
af()
};
ap.$on("$stateChangeSuccess",af);
function ag(ax,at,av){var aw=Z.get(ax,W(aq));
var au=al(ax,at);
ar.push({state:aw||{name:ax},params:at,hash:au});
ad[au]=av
}function al(at,au){if(!o(at)){throw new Error("state should be a string")
}if(O(au)){return at+h(au)
}au=ap.$eval(au);
if(O(au)){return at+h(au)
}return at
}function af(){for(var at=0;
at<ar.length;
at++){if(aj(ar[at].state,ar[at].params)){ak(aq,ad[ar[at].hash])
}else{ao(aq,ad[ar[at].hash])
}if(ai(ar[at].state,ar[at].params)){ak(aq,ah)
}else{ao(aq,ah)
}}}function ak(au,at){ae(function(){au.addClass(at)
})
}function ao(au,at){au.removeClass(at)
}function aj(at,au){return Z.includes(at.name,au)
}function ai(at,au){return Z.is(at.name,au)
}af()
}]}
}G.module("ui.router.state").directive("uiSref",p).directive("uiSrefActive",T).directive("uiSrefActiveEq",T).directive("uiState",U);
H.$inject=["$state"];
function H(aa){var Z=function(ab,ac){return aa.is(ab,ac)
};
Z.$stateful=true;
return Z
}t.$inject=["$state"];
function t(aa){var Z=function(ac,ad,ab){return aa.includes(ac,ad,ab)
};
Z.$stateful=true;
return Z
}G.module("ui.router.state").filter("isState",H).filter("includedByState",t)
})(window,window.angular);