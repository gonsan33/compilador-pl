(module
  (type $_sig_i32i32i32 (func (param i32 i32 i32) ))
  (type $_sig_i32ri32 (func (param i32) (result i32)))
  (type $_sig_i32 (func (param i32)))
  (type $_sig_ri32 (func (result i32)))
  (type $_sig_void (func ))
  (import "runtime" "exceptionHandler" (func $exception (type $_sig_i32)))
  (import "runtime" "print" (func $print (type $_sig_i32)))
  (import "runtime" "read" (func $read (type $_sig_ri32)))
  (memory 2000)
  (global $SP (mut i32) (i32.const 0)) ;; start of stack
  (global $MP (mut i32) (i32.const 0)) ;; mark pointer
  (global $NP (mut i32) (i32.const 131071996)) ;; heap 2000*64*1024-4
  (start $main)
(func $reserveStack (param $size i32) (result i32)
  global.get $MP
  global.get $SP
  global.set $MP
  global.get $SP
  local.get $size
  i32.add
  global.set $SP
  global.get $SP
  global.get $NP
  i32.gt_u
  if
    i32.const 3
    call $exception
  end
)
(func $freeStack
  global.get $MP
  global.set $SP
  global.get $MP
  i32.load
  global.set $MP
)
(func $reserveHeap (param $size i32) (result i32)
  global.get $NP
  local.get $size
  i32.sub
  global.set $NP
  global.get $NP
  global.get $SP
  i32.lt_u
  if
    i32.const 4
    call $exception
  end
  global.get $NP
)

(func $copyn (type $_sig_i32i32i32) ;; copy $n i32 slots from $src to $dest
  (param $src i32)
  (param $dest i32)
  (param $n i32)
  block
    loop
      local.get $n
      i32.eqz
      br_if 1
      local.get $n
      i32.const 1
      i32.sub
      local.set $n
      local.get $dest
      local.get $src
      i32.load
      i32.store
      local.get $dest
      i32.const 4
      i32.add
      local.set $dest
      local.get $src
      i32.const 4
      i32.add
      local.set $src
      br 0
    end
  end
)
(func $suma3 (result i32)
  (local $localsStart i32)
  (local $temp i32)
  i32.const 12
  call $reserveStack
  local.set $temp
  global.get $MP
  local.get $temp
  i32.store
  global.get $MP
  i32.const 4
  i32.add
  local.set $localsStart
i32.const 0
local.get $localsStart
i32.add
i32.load
i32.const 4
local.get $localsStart
i32.add
i32.load
i32.add
i32.const 8
local.get $localsStart
i32.add
i32.load
i32.add
call $freeStack
return
  i32.const 0
  call $freeStack
)
(func $main
  (local $localsStart i32)
  (local $temp i32)
  i32.const 4
  call $reserveStack
  local.set $temp
  global.get $MP
  local.get $temp
  i32.store
  global.get $MP
  i32.const 4
  i32.add
  local.set $localsStart
i32.const 0
local.get $localsStart
i32.add
  i32.const 8
  call $reserveHeap
i32.store
i32.const 0
local.get $localsStart
i32.add
i32.load
i32.const 0
i32.add
i32.const 7
i32.store
i32.const 0
local.get $localsStart
i32.add
i32.load
i32.const 4
i32.add
i32.const 5
i32.store
global.get $SP
i32.const 0
i32.add
i32.const 0
local.get $localsStart
i32.add
i32.load
i32.const 0
i32.add
i32.load
i32.store offset=4
global.get $SP
i32.const 4
i32.add
i32.const 0
local.get $localsStart
i32.add
i32.load
i32.const 4
i32.add
i32.load
i32.store offset=4
global.get $SP
i32.const 8
i32.add
i32.const 30
i32.store offset=4
call $suma3
  call $print
  call $freeStack
)
)
