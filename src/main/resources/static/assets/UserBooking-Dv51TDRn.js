import{u as p,r as a,j as e}from"./index-WSm8V2ST.js";import{T as d}from"./TitleHeader-CGwkCU68.js";import"./iconBase-C31Zs2Ot.js";const o=["예약 목록","예약 완료 내역"],u=()=>{var x,r;const c=p(),i=()=>{c(-1)},[t,n]=a.useState(0);return a.useState({}),a.useEffect(()=>{console.log("카테고리",t)},[t]),e.jsxs("div",{className:"flex flex-col gap-[30px]",children:[e.jsx(d,{icon:"back",title:"여행",onClick:i}),e.jsx("div",{className:"px-[32px]",children:e.jsx("ul",{className:"flex items-center",children:o.map((s,l)=>e.jsx("li",{className:`cursor-pointer w-full flex justify-center items-center
                            pt-[17px] pb-[16px]
                            ${t===l?"text-primary border-b-[2px] border-primary":"text-slate-400 border-b border-slate-200"}`,onClick:()=>{n(l)},children:s},l))})}),e.jsxs("div",{className:"px-[28px] mb-[40px]",children:[t===0&&e.jsx("ul",{className:"flex flex-col gap-[40px]",children:(x=tripListData.beforeTripList)==null?void 0:x.map((s,l)=>e.jsxs("li",{className:"flex items-center justify-between",children:[e.jsxs("div",{className:"flex items-center gap-[29px]",children:[e.jsx("div",{className:"w-[100px] h-[100px] bg-slate-100 rounded-full",children:e.jsx("img",{src:"",alt:""})}),e.jsxs("div",{className:"flex flex-col gap-[5px]",children:[e.jsx("h3",{className:"text-[24px] text-slate-700 font-semibold",children:s.title}),e.jsxs("p",{className:"text-[18px] text-slate-500",children:[e.jsx("span",{children:s.startAt}),"~",e.jsx("span",{children:s.endAt})]})]})]}),e.jsx("button",{className:"w-[36px] h-[36px] bg-slate-100 px-[10px] py-[10px] rounded-full",onClick:()=>{navigateGoTrip(s)},children:e.jsx(AiOutlinePlus,{className:"text-slate-400"})})]},l))}),t===1&&e.jsx("ul",{className:"flex flex-col gap-[40px]",children:(r=tripListData.afterTripList)==null?void 0:r.map((s,l)=>e.jsxs("li",{className:"flex items-center justify-between",children:[e.jsxs("div",{className:"flex items-center gap-[29px]",children:[e.jsx("div",{className:"w-[100px] h-[100px] bg-slate-100 rounded-full",children:e.jsx("img",{src:"",alt:""})}),e.jsxs("div",{className:"flex flex-col gap-[5px]",children:[e.jsx("h3",{className:"text-[24px] text-slate-700 font-semibold",children:s.title}),e.jsxs("p",{className:"text-[18px] text-slate-500",children:[e.jsx("span",{children:s.startAt}),"~",e.jsx("span",{children:s.endAt})]})]})]}),e.jsx("button",{className:"w-[36px] h-[36px] bg-slate-100 px-[10px] py-[10px] rounded-full",onClick:()=>{navigateGoTrip(s)},children:e.jsx(AiOutlinePlus,{className:"text-slate-400"})})]},l))})]})]})};export{u as default};
