
#include "llvm/Analysis/CFGPrinter.h"
#include "llvm/IR/IRBuilder.h"
#include "llvm/IR/Function.h"
#include "llvm/IR/LegacyPassManager.h"
#include "llvm/IR/Module.h"
#include "llvm/Support/FileSystem.h"
#include "llvm/Support/raw_ostream.h"
#include "llvm/Transforms/IPO/PassManagerBuilder.h"
#include "llvm/Pass.h"
#include "llvm/IR/Type.h"

using namespace llvm;
static LLVMContext Context;

namespace {

class ExamplePass : public ModulePass {

 public:
  static char ID;
  ExamplePass() : ModulePass(ID) { }
  
  bool doInitialization(Module &M) override;
  bool runOnModule(Module &M) override;

};

}  // namespace

char ExamplePass::ID = 0;

bool ExamplePass::doInitialization(Module &M) {

  return true;

}

bool ExamplePass::runOnModule(Module &M) {
  errs() << "runOnModule\n";
  // Declare Type
  IntegerType *Int32Ty = IntegerType::getInt32Ty(M.getContext());
  Type *VoidTy = Type::getVoidTy(M.getContext());

  // Insert a new IR
  // ref : https://ucasfl.github.io/2018/07/08/LLVM-Pass-and-APA-Introduction/
  Function *F = M.getFunction("main");
  Instruction *inst = &(*F->begin()->begin()); // find the 1st instr of main
  IRBuilder<> IRB(inst);

  // 1. Create Call to the debug function
  FunctionType *FnTy = FunctionType::get(VoidTy, {Int32Ty}, false);
  FunctionCallee Fn = M.getOrInsertFunction("debug",FnTy);
  IRB.CreateCall(Fn, ConstantInt::get(Int32Ty, 9527));
  
  // 2. point argv[1] to mystr
  std::string mystr = "aesophor is ghost !!!";
  Value *ptr_mystr = IRB.CreateGlobalStringPtr(mystr);
  
  Value *Base = F->getArg(1);
  Value *gep = IRB.CreateGEP(Base, IRB.getInt32(1));
  IRB.CreateStore(ptr_mystr, gep);
  
  // 3. replace argc with 9487
  Value *myargc = ConstantInt::get(Int32Ty, 9487);
  Value *argc_base = F->getArg(0);
  argc_base->replaceAllUsesWith(myargc);
  
  return true;
  
}

static void registerExamplePass(const PassManagerBuilder &,
                                           legacy::PassManagerBase &PM) {

  PM.add(new ExamplePass());

}

static RegisterStandardPasses RegisterExamplePass(
    PassManagerBuilder::EP_OptimizerLast, registerExamplePass);

static RegisterStandardPasses RegisterExamplePass0(
    PassManagerBuilder::EP_EnabledOnOptLevel0, registerExamplePass);

