import{u as m,r as s,j as e}from"./index-DW3n6Q7M.js";import{T as d}from"./TitleHeader-B38qZ9mj.js";import{M as f,S as u}from"./ScheduleDay-AM4HrvwI.js";import{T as h}from"./index-FNZB9EPz.js";import{B as g}from"./button-CQF-brAv.js";import"./index-42XTSeCz.js";import"./iconBase-CG0cGfAC.js";import"./index-BFYPnQPV.js";import"./index-Dul2LMQI.js";import"./jwt-C9teUdx2.js";import"./axios-upsvKRUO.js";import"./tripAtom-DjhJfGOm.js";import"./index-CYxM6BHZ.js";import"./context-CbU8ZgmM.js";import"./KeyCode-DNlgD2sM.js";import"./ContextIsolator-xUhtV4-I.js";import"./Keyframes-BbKayN__.js";const L=()=>{const r=m(),l=()=>{r(-1)},[j,i]=s.useState(""),[o,n]=s.useState("여행기 제목"),[p,x]=s.useState(""),c=t=>{const a=t.target.files[0];a&&i(URL.createObjectURL(a))};return e.jsxs("div",{children:[e.jsx(d,{icon:"back",onClick:l,title:"여행기 공유"}),e.jsxs("div",{className:"mt-[60px] py-[40px] flex flex-col gap-[40px]",children:[e.jsxs("div",{className:"flex flex-col gap-[48px] px-[32px]",children:[e.jsxs("div",{className:`w-full h-[160px]\r
                      flex items-center gap-[20px]`,children:[e.jsx("input",{id:"fileUpload",type:"file",accept:"image/png, image/jpeg",onChange:c,className:"hidden"}),e.jsx("label",{htmlFor:"fileUpload",className:`\r
            w-[160px] h-[160px] \r
            flex items-center justify-center\r
            bg-slate-100 rounded-lg\r
            hover:bg-[#e9eef3]\r
            transition duration-300\r
            cursor-pointer shrink-0`,children:e.jsx(f,{size:60,className:"text-slate-400"})})]}),e.jsx("input",{type:"text",className:` px-[10px] py-[10px]\r
            text-slate-400 text-[36px] font-semibold\r
            border-b border-slate-200\r
           focus:border-primary focus:outline-none\r
           `,value:o,onChange:t=>n(t.target.value)}),e.jsx(h,{value:p,onChange:t=>x(t.target.value),placeholder:"이번 여행은 어떠셨나요? 여행에 대한 감상과 여행에서 경험한 꿀팁들을 남겨 다른 회원님들과 공유해보세요 !",autoSize:{minRows:3,maxRows:5},variant:"borderless",className:"placeholder: text-[24px] placeholder: text-slate-400"})]}),e.jsx("div",{className:"px-[32px]",children:e.jsx(u,{showMap:!1})}),e.jsx("div",{className:"px-[32px] w-full",children:e.jsx(g,{type:"primary",className:`w-full h-[80px] \r
                    text-slate-50 text-[24px] font-semibold`,children:"완료"})})]})]})};export{L as default};
